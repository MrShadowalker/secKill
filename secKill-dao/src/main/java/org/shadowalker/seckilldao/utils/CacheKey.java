package org.shadowalker.seckilldao.utils;

/**
 * Redis 缓存 Key 值
 *
 * @author Shadowalker
 */
public enum CacheKey {

    // 测试编码
    STOCK("stock_test"),
    PRODUCT("product_test"),

    // 正式编码
    HASH_KEY("seckill_v1_user_hash"),
    LIMIT_KEY("seckill_v1_user_limit"),
    STOCK_COUNT("seckill_v1_stock_count"),
    USER_HAS_ORDER("seckill_v1_user_has_order");

    private final String key;

    CacheKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
