package com.cybersoft.capstone.specification;

import com.cybersoft.capstone.entity.Platforms;
import org.springframework.data.jpa.domain.Specification;

public class PlatformSpecification {

    public static Specification<Platforms> isOrphan(Boolean isOrphan) {
        return (root, query, cb) -> {
            if (isOrphan == null) { return null;}
            return isOrphan
                ? cb.isNull(root.get("parent"))
                : cb.isNotNull(root.get("parent"));
        };
    }

}
