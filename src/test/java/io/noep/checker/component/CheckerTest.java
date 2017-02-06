package io.noep.checker.component;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by NOEP-2016 on 2017-02-07.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CheckerTest {

    @Autowired
    private Checker checker;

    @Test
    @Ignore
    public void parseIntegrationTest() {
        checker.checkUrl();
    }
}
