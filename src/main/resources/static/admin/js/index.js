var stompClient = null;
/**
 * 建立webshocket连接
 */
function connect() {
	// 建立连接对象（还未发起连接）
	var socket = new SockJS('/gs-guide-websocket');
	// 获取 STOMP 子协议的客户端对象
	stompClient = Stomp.over(socket);
	// 向服务器发起websocket连接并发送CONNECT帧
	stompClient.connect({}, function(frame) {
		// 连接成功时（服务器响应 CONNECTED 帧）的回调方法
		// setConnected(true);
		console.log('Connected: ' + frame);
		stompClient.subscribe('/topic/greetings', function(greeting) {
			showGreeting(JSON.parse(greeting.body).content);
		});
	}, function errorCallBack(error) {
		// 连接失败时（服务器响应 ERROR 帧）的回调方法
	});
}

function showGreeting(content) {
	$("#daiban_count").text(content);
}
$(function() {
	// 监听信息
	connect();
});