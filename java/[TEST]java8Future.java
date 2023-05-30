package java;

import java.time.LocalTime;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class java8Future {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//1 -> future 사용 방법
//		CompletableFuture<String> future = new CompletableFuture<String>();
//		Executors.newCachedThreadPool().submit(() -> {
//			Thread.sleep(1000);
//			future.complete("finished");
//			//future.cancel(false);//예외처리
//			return null;
//		});
//		log(future.get());
		
		//2 -> cancel에 대한 예외 처
//		Future<String> completableFuture = CompletableFuture.completedFuture("skip!");
//		String result = completableFuture.get();
//		log(result);
		
		//3 -> 직접 스레드를 생성하지않고 async로 처리 함, 람다식으로 2초간 슬립 후 get호출로 2초간 블럭을 하고 리턴.
//		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {//return 없는 supplyAsync  = runAsync
//			log("Strarting...");
//			try {
//				Thread.sleep(2000);
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//			return "finished supply";
//		});
//		log("get() : " + future.get());
		
		//4 -> exception handling
//		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
//			String name = null;
//			if(name == null) {
//				throw new RuntimeException("computation error");
//			}
//			return "hello, " + name;
//		}).handle((s,t) -> s != null ? s : "hello, world");
//		log(future.get());
		
		//5 -> thenApply : future1이 처리되면 그 결과를 가지고 다른 작업도 수행하도록 구현 / thenAccept는 인자느 있지만 리턴값이 없음.
//		CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "future1");
//		CompletableFuture<String> future2 = future1.thenApply(s -> s + "+ future2");
//		log("future1.get() : " + future1.get());
//		log("future2.get() : " + future2.get());
		
		//6 -> 여러 작업을 순차적으로 수행
//		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello")
//																								.thenCompose(s -> CompletableFuture.supplyAsync(() -> s + "world"))
//																								.thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " java"));
//		log(future.get());
		
		//7 -> 여러 작업을 동시에 수행 / 동시작업은 그대로지만, 다른 스레드에서 동작하게끔 변경 thenApply -> thenApplyAsync 
//		CompletableFuture.supplyAsync(() -> "hello");
//		CompletableFuture<String> future1 = CompletableFuture
//																								.supplyAsync(() -> "future1")
//																								.thenApply((s) -> {
//																									log("starting future1");
//																									try {
//																										Thread.sleep(1000);
//																									} catch (Exception e) {
//																										System.out.println(e.getMessage());
//																									}
//																									return s + "!";
//																								});
//		
//		CompletableFuture.supplyAsync(() -> "hello");
//		CompletableFuture<String> future2 = CompletableFuture
//																								.supplyAsync(() -> "future2")
//																								.thenApply((s) -> {
//																									log("starting future2");
//																									try {
//																										Thread.sleep(1000);
//																									} catch (Exception e) {
//																										System.out.println(e.getMessage());
//																									}
//																									return s + "!";
//																								});
//		
//		future1.thenCombine(future2, (s1,s2) -> s1 + "+ " + s2)
//															.thenAccept((s) -> log(s));
		
		//8 -> 모든 future의 결과를 받아서 처리 : anyof다르게 stream api를 사용함
		CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "future1");
		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "future2");
		CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "future3");
		
		//return이 없는 키워드를 넣기위해 void를 넣음
		CompletableFuture<Void> combindFuture = CompletableFuture.allOf(future1, future2, future3);
		log("get() : " + combindFuture.get());
		log("future1.isDone() : " + future1.isDone());
		log("future2.isDone() : " + future2.isDone());
		log("future3.isDone() : " + future3.isDone());
		
		String combined = Stream.of(future1, future2, future3)
				.map(CompletableFuture::join)
				.collect(Collectors.joining(" + "));
		log("combined : " + combined);
	}
	
	public static void log(String msg) {
			System.out.println(LocalTime.now() + " ("
					+ Thread.currentThread().getName() + ") " +  msg);
	}
}
