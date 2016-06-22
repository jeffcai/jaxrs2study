package com.jeffcaijf.jaxrs2.cxf.client;

import com.jeffcaijf.jaxrs2.cxf.Item;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by jeffcai on 6/22/2016.
 */
public class AsyncInvoker {

    public static void main(String[] args) {
        // call in parallel with executor service
        syncInvoke();

        asyncInvokeWithFuture();

        // seems not working
        asyncInvokeWithCallback();
    }

    private static void syncInvoke() {
        ExecutorService executorService = Executors.newFixedThreadPool(50);

        for (int i  = 0; i < 100; i++) {
            final int j = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    Client client = ClientBuilder.newClient().register(JacksonJsonProvider.class);

                    List<Item> items = client.target("http://localhost:8080/myapp/resource/itemservice/query")
                            .request().get(new GenericType<List<Item>>() {
                            });
                    System.out.println("index " + j + " sync returned items: " + items);
                }
            });
        }

        // call shutdown to terminate the executuor otherwise the main thread would not stop
        executorService.shutdown();
    }

    private static void asyncInvokeWithFuture() {
        Client client = ClientBuilder.newClient().register(JacksonJsonProvider.class);

        // Use Future
        Future<Response> future = client.target("http://localhost:8080/myapp/resource/itemservice/query")
                .request()
                .async().get();

        Response res = null;
        try {
            // block until complete
            res = future.get(5, TimeUnit.SECONDS);
            List<Item> items = res.readEntity(new GenericType<List<Item>>() {
            });
            System.out.println("async with future returned items: " + items);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            // ensure response is closed
            res.close();
        }
    }

    private static void asyncInvokeWithCallback() {
        System.out.println("asyncInvokeWithCallback starts ...");
        Client client = ClientBuilder.newClient().register(JacksonJsonProvider.class);

        // Use Callback
        Future<Response> future = client.target("http://localhost:8080/myapp/resource/itemservice/query")
                .request()
                .async().get(new InvocationCallback<Response>() {

            @Override
            public void completed(Response response) {
                System.out.println("async with callback completed ...");
                List<Item> items = response.readEntity(new GenericType<List<Item>>() {
                });
                System.out.println("async with callback returned items: " + items);
            }

            @Override
            public void failed(Throwable throwable) {
                throwable.printStackTrace();
            }
        });

        System.out.println("Future done? " + future.isDone());
    }

}
