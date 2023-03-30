package myapp.webshop.service;

import myapp.webshop.dto.BucketDTO;
import myapp.webshop.entities.Bucket;
import myapp.webshop.entities.User;

import java.util.List;

public interface BucketService {

    Bucket createBucket(User user, List<Long> productIds);

    void addProducts(Bucket bucket, List<Long> productIds);

    BucketDTO getBucketByUser(String name);
}
