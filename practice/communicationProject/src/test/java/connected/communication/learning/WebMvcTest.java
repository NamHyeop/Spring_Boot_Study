package connected.communication.learning;

import connected.communication.dto.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class WebMvcTest {
    @InjectMocks TestController testController;
    /**
     * 컨트롤러를 사용하기 위해 MockMVC 활용
     */
    MockMvc mockMvc;

    @Controller
    public static class TestController{
        @GetMapping("/test/ignore-null-value")
        public Response ignoreNullValueTest(){
            return Response.success();
        }
    }

    @BeforeEach
    void beforeEach(){
        /**
         * TestController를 사용하기 위해 MockMvcBuilder를 띄어준다.
         */
        mockMvc = MockMvcBuilders.standaloneSetup(testController).build();
    }

    @Test
    public void ignoreNullValueJsonResponseTest() throws Exception{
        //given
        //when
        //then
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/test/ignore-null-value"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.result").doesNotExist());
    }
}
