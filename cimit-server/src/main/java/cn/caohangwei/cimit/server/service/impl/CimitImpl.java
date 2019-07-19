package cn.caohangwei.cimit.server.service.impl;

import cn.caohangwei.cimit.common.CimitRule;
import cn.caohangwei.cimit.core.LimiterFactory;
import cn.caohangwei.cimit.server.service.CimitService;
import org.springframework.stereotype.Service;

@Service
public class CimitImpl implements CimitService {

    @Override
    public boolean acquire(CimitRule rule) {
        return LimiterFactory.getLeakyBucketLimiter(rule).acquire();
    }

    @Override
    public boolean tryAcquire(CimitRule rule) {
        return LimiterFactory.getLeakyBucketLimiter(rule).tryAcquire();
    }
}
