package guru.springframework.repositories.reactive;

import guru.springframework.domain.Recipe;
import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.BDDAssertions.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class RecipeReactiveRepositoryTest {

    @Autowired
    RecipeReactiveRepository recipeReactiveRepository;

    @Test
    public void testRecipeSave() throws Exception
    {
        Recipe recipe = new Recipe();
        recipe.setDescription("YUMMY TREAT");
        recipeReactiveRepository.save(recipe).block();
        Long count = recipeReactiveRepository.count().block();
        then(count).isEqualTo(Long.valueOf(1L));
    }
}
