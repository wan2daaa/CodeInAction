package me.wane.jpa.relationship_mapping.two_way;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class TwoWayTest {

    @Autowired
    private TwoWay twoWay;

    @Test
    void test1() {
        twoWay.save();

        twoWay.find();
    }
}