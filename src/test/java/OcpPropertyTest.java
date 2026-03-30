import metier.IMetier;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import presentation.Presentation2;

import static org.junit.Assert.assertEquals;

public class OcpPropertyTest {

    @Test
    public void propertySelection_choisitDao2_300() {

        System.setProperty("dao.target", "dao2");

        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext();

        ctx.register(Presentation2.class);
        ctx.refresh();

        IMetier m = ctx.getBean(IMetier.class);

        assertEquals(300.0, m.calcul(), 1e-6);

        ctx.close();
    }
}
