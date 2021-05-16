package org.shadowalker.seckillweb.controller;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.shadowalker.seckilldao.utils.CacheKey;
import org.shadowalker.seckillservice.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 库存 Controller
 *
 * @author Shadowalker
 */
@Slf4j
@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    /**
     * 查询库存：通过数据库查询库存
     *
     * @param sid
     * @return
     */
    @RequestMapping("/getStockByDB/{sid}")
    @ResponseBody
    public String getStockByDB(@PathVariable int sid) {
        int count;
        try {
            count = stockService.getStockCountByDB(sid);
        } catch (Exception e) {
            log.error("查询库存失败:【{}】", e.getMessage());
            return "查询库存失败";
        }
        log.info("商品Id:【{}】 剩余库存为:【{}】", sid, count);
        return String.format("商品Id: %d 剩余库存为：%d", sid, count);
    }

    /**
     * 查询库存：通过缓存查询库存
     * 缓存命中：返回库存
     * 缓存未命中：查询数据库写入缓存并返回
     *
     * @param sid
     * @return
     */
    @RequestMapping("/getStockByCache/{sid}")
    @ResponseBody
    public String getStockByCache(@PathVariable int sid) {
        Integer count;
        try {
            count = stockService.getStockCount(sid);
        } catch (Exception e) {
            log.error("查询库存失败:【{}】", e.getMessage());
            return "查询库存失败";
        }
        log.info("商品Id:【{}】 剩余库存为:【{}】", sid, count);
        return String.format("商品Id: %d 剩余库存为：%d", sid, count);
    }


    /********************************************* Redisson *********************************************/

    @Autowired
    private Redisson redisson;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/deduct_stock")
    public String deductStock() {
        String lockKey = CacheKey.PRODUCT.getKey();
        // String clientId = UUID.randomUUID().toString();
        // log.info(clientId);

        // V6 Redisson initial
        RLock redissonLock = redisson.getLock(lockKey);
        int realStock = 0;

        // V1 加 try-catch-finally 并最终释放锁，以避免死锁。
        try {

            // V0 入门级
            // 问题：如果下面执行业务代码出现异常，锁无法释放，死锁。
            // Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, "seckill");

            // V3 还没有设置过期时间，JVM 又挂了，仍然死锁。需要原子操作。
            // Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, "seckill", 10, TimeUnit.SECONDS);

            // V4 如果下面业务代码执行时间超过锁过期时间，会把第二个线程的锁释放掉，第三个线程加锁。同理，第二个线程继续执行，会把第三个线程锁释放……
            // 高并发场景下，可能锁会一直失效。
            // 自己加的锁要自己释放。
            // Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, clientId, 10, TimeUnit.SECONDS);

            // V5 如果执行时间过长，锁过期。可以开启异步线程实时监测任务执行情况，如果没有执行完，则把锁续期。但一般不要自己写，很容易出 bug。

            // V2 JVM 挂掉，死锁，需要过期时间
            // stringRedisTemplate.expire(lockKey, 10, TimeUnit.SECONDS);

            // if (!result) {
            //     return "error";
            // }

            // JVM 锁，集群下无用
            // synchronized (this) {

            // V6 Redisson lock
            // 底层方法 tryLockInnerAsync() 底层 Lua 脚本保证原子操作
            redissonLock.lock(15, TimeUnit.SECONDS);

            // V7 目前初始化的 Redisson 是单机模式，如果是主从模式呢？
            // 主节点上锁了，还没有同步给从节点，主节点挂了，从变为主，于是又会上一把锁。
            // 不过其实问题不大，因为也就有两个线程同时执行，微小可能出现少数超卖。
            // Redlock（仿照 ZAB 协议机制，超半数加锁成功才能算上锁，一般不用，性能低）
            // zookeeper（主从超半数同步，选举机制，ZAB 协议，断开连接不会死锁）

            // V8 Redis 是单线程，所以如果好几万并发来抢锁，其实还是串行化执行，并发量只有十万。
            // 分段锁思想，集群分片

            // 业务代码
            int stock = Integer.parseInt(Objects.requireNonNull(stringRedisTemplate.opsForValue().get(CacheKey.STOCK.getKey())));
            log.info("stock:{}", stock);
            if (stock > 0) {
                realStock = stock - 1;
                log.info("realStock:{}", realStock);
                stringRedisTemplate.opsForValue().set(CacheKey.STOCK.getKey(), realStock + "");
                System.out.println("扣减库存成功，剩余库存：" + realStock + "");
            } else {
                System.out.println("库存不足，扣减库存失败。");
            }

            // }
            // finally {
            //     // V4 解铃还须系铃人
            //     if (clientId.equals(stringRedisTemplate.opsForValue().get(lockKey))) {
            //         // V1
            //         stringRedisTemplate.delete(lockKey);
            //     }

        } finally {
            // V6 Redisson unlock
            redissonLock.unlock();
        }
        return "秒杀结束，剩余库存：" + realStock + "";
    }

    /**
     * 正经人没人用 redlock，牺牲性能
     * 还不如 zk，至少没 bug
     *
     * @return
     */
    @RequestMapping("/redlock")
    public String redlock() {
        String lockKey = "product_001";

        // 这里需要自己实例化不同 Redis 实例的 Redisson 客户端连接，这里只是伪代码用一个 Redisson 客户端简化了。
        RLock lock1 = redisson.getLock(lockKey);
        RLock lock2 = redisson.getLock(lockKey);
        RLock lock3 = redisson.getLock(lockKey);

        /**
         * 根据多个 RLock 对象构建 RedissonRedLock（最核心的差别就在这里）
         */
        RedissonRedLock redLock = new RedissonRedLock(lock1, lock2, lock3);

        try {
            /**
             * waitTimeout 尝试获取锁的最大等待时间，超过这个值，则认为获取锁失败
             * leaseTime   锁的持有时间，超过这个时间锁会自动失效（值应该设置为大于业务处理的时间，确保在锁有效期内业务能处理完）
             */
            boolean res = redLock.tryLock(10, 30, TimeUnit.SECONDS);
            if (res) {
                // 成功获得锁，此处处理业务代码
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("lock fail");
        } finally {
            // 无论如何，最后都要解锁。
            redLock.unlock();
        }

        return "end";
    }
}
