package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
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
public class CategoryReactiveRepositoryTest {

    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    @Before
    public void deleteAll()
    {
        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void testCategorySave() throws Exception
    {
        Category cat = new Category();
        cat.setDescription("CATEGORY OF FOOD");
        categoryReactiveRepository.save(cat).block();
        Long count = categoryReactiveRepository.count().block();
        then(count).isEqualTo(Long.valueOf(1L));
    }


    @Test
    public void testFindByDescription()
    {
        Category cat = new Category();
        cat.setDescription("CATEGORY OF FOOD");
        categoryReactiveRepository.save(cat).then().block();

        Category received = categoryReactiveRepository.findByDescription("CATEGORY OF FOOD").block();

        then(received).isNotNull();
    }
}
