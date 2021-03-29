package cn.monitor4all.miaoshaweb.controller;

import cn.monitor4all.miaoshadao.mapper.UserMapper;
import cn.monitor4all.miaoshaservice.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private UserMapper userMapper;

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

}
