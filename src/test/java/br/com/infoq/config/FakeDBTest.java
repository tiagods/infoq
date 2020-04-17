package br.com.infoq.config;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.infoq.StartApp;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {StartApp.class, H2ConfigTest.class})
public class FakeDBTest {

}
