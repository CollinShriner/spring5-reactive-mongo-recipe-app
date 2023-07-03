package guru.springframework.repositories.reactive;

import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UOMReactiveRepositoryTest {

    @Autowired
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @Before
    public void deleteAll()
    {
        unitOfMeasureReactiveRepository.deleteAll().block();
    }

    @Test
    public void testUOMSave() throws Exception
    {
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setDescription("MEASURE");
        unitOfMeasureReactiveRepository.save(uom).block();
        Long count = unitOfMeasureReactiveRepository.count().block();
        then(count).isEqualTo(Long.valueOf(1L));
    }


    @Test
    public void testFindByDescription()
    {
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setDescription("MEASURE");
        unitOfMeasureReactiveRepository.save(uom).then().block();

        UnitOfMeasure received = unitOfMeasureReactiveRepository.findByDescription("MEASURE").block();

        then(received).isNotNull();
    }
}
