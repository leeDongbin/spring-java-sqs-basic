package com.example.sqs;

import com.example.sqs.dto.EcmDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.concurrent.*;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/** HYOSUNG POS
 *   StampControllerTest
 *   > description : 포인트 컨트롤러 테스트
 *   > issueNo : server-146
 *   > author : dongbinlee
 */
//@ActiveProfiles("local")
@SpringBootTest
@AutoConfigureMockMvc
public class SqsSendTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void TransferSqsTest() throws Exception {
        System.out.println("test Start ================================");
        Runnable task = () -> {
                System.out.println(">>>>>>   go :");
                try {
                    IntStream.range(0, 1000).forEach((i) -> {
                        try {
                            EcmDto request = EcmDto.builder().ecmId(String.valueOf(i)).build();
                            ResultActions resultActions = this.mockMvc.perform(post("/send2")
                                    .content(objectMapper.writeValueAsString(request))
                                    .contentType(MediaType.APPLICATION_JSON_UTF8));

                            System.out.println(">>>>>>   i :"+i);
                            resultActions.andDo(print());
                            resultActions.andExpect(status().isOk());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
        };

        Thread t2 = new Thread(task);
        t2.start();
        t2.join();

        System.out.println("test End ================================");
    }

    @Test
    public void TransferSqsTest2() throws Exception {
        System.out.println("test Start ================================");

        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(7);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,5, 3L, TimeUnit.SECONDS, workQueue);
        Runnable task = () -> {
            System.out.println(">>>>>>   go :");
            try {
                IntStream.range(0, 1000).forEach((i) -> {
                    try {
                        EcmDto request = EcmDto.builder().ecmId(String.valueOf(i)).build();
                        ResultActions resultActions = this.mockMvc.perform(post("/send2")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON_UTF8));

                        System.out.println(">>>>>>   i :"+i);
                        resultActions.andDo(print());
                        resultActions.andExpect(status().isOk());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };

        Future future = threadPoolExecutor.submit(task);
        System.out.println("test End ================================");
        System.out.println("test End ================================"+future.get());
    }

    @Test
    public void TransferSqsTest3() throws Exception {
        System.out.println("test Start ================================");

        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(7);
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.initialize();
        Runnable task = () -> {
            System.out.println(">>>>>>   go :");
            try {
                IntStream.range(0, 10).forEach((i) -> {
                    try {
                        EcmDto request = EcmDto.builder().ecmId(String.valueOf(i)).build();
                        ResultActions resultActions = this.mockMvc.perform(post("/send2")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON_UTF8));

                        System.out.println(">>>>>>   i :"+i);
                        resultActions.andDo(print());
                        resultActions.andExpect(status().isOk());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };


        Future future = threadPoolTaskExecutor.submit(task);
        System.out.println("test End ================================");
        System.out.println("test End ================================"+future.get());
    }

    @Test
    public void TransferSqsTest4() throws Exception {
        System.out.println("test Start ================================");

        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(7);
        ExecutorService service = Executors.newFixedThreadPool(5);
        Runnable task = () -> {
            System.out.println(">>>>>>   go :");
            try {
                IntStream.range(0, 5).forEach((i) -> {
                    try {
                        EcmDto request = EcmDto.builder().ecmId(String.valueOf(i)).build();
                        ResultActions resultActions = this.mockMvc.perform(post("/send2")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON_UTF8));

                        System.out.println(">>>>>>   i :"+i);
                        resultActions.andDo(print());
                        resultActions.andExpect(status().isOk());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };


        Future future = service.submit(task);
        System.out.println("test End ================================");
        System.out.println("test End ================================"+future.get());
    }

}