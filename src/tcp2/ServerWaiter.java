package tcp2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerWaiter extends Thread{
    private final Socket socket;

    ServerWaiter(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("==================");
            System.out.println("有一个客户端连接上来了");
            InputStream is = socket.getInputStream();       // 返回此套接字的输入流
            InputStreamReader isReader = new InputStreamReader(is, "UTF-8"); //创建一个使用命名字符集的InputStreamReader。
            Scanner scanner = new Scanner(isReader);
            //没有scanner，需要手动找\r\n，很麻烦


            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osWriter = new OutputStreamWriter(os, "UTF-8");
            PrintWriter printWriter = new PrintWriter(osWriter);

            //一个连接可以发送多个请求
            //服务器在这个循环中专心处理客户端1的事情
            //不知道客户端2已经连接上来了
            //知道客户端1关闭连接了（hasNextLine() 返回false）
            //服务器才能重新处理下一个客户端连接
            while (scanner.hasNextLine()) {
                String request = scanner.nextLine();
                System.out.println("+++++++++++++++");
                System.out.println(request);

                //处理请求-->响应
                String response = request;

                //发送响应，也需要带着\r\n
                printWriter.println(response);
                printWriter.flush();        //千万不要忘记

            System.out.println("+++++++++++++++++++++");
        }
        System.out.println("==============");
    }catch (IOException e){
            e.printStackTrace();
        }
    }
}
