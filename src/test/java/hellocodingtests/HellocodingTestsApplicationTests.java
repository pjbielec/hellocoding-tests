package hellocodingtests;

import hellocodingtests.api.StockAPI;
import hellocodingtests.model.Stock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HellocodingTestsApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StockAPI stockAPI;

	@Test
	public void findAll() throws Exception{
		Map<Long, Stock> data = StockData.INSTANCE.getStocks();
		given(stockAPI.findAll()).willReturn(ResponseEntity.ok(data));

		this.mockMvc.perform(get("/api/v1/stocks"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.*", hasSize(data.size())));
	}

}
