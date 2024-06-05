package me.wane.jpa.relationship_mapping;

import me.wane.jpa.relationship_mapping.one.RelationShipMapping;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class RelationShipMappingTest {

    @Autowired
    private RelationShipMapping relationShipMapping;

    @Test
    void test1() {
        relationShipMapping.save();

        relationShipMapping.find();
    }

    @Test
    void test2() {
        relationShipMapping.save();

        relationShipMapping.jpqlFind();
    }

    @Test
    void update() {
        relationShipMapping.save();

        relationShipMapping.update();
    }

    @Test
    void delete() {
        relationShipMapping.save();

        relationShipMapping.delete();
    }
}