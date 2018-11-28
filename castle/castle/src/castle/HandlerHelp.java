package castle;

public class HandlerHelp extends Handler{

	
	public HandlerHelp(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCmd(String word) {
		System.out.println("迷路了吗？你可以做的命令有：go bye help");
		System.out.println("如：\tgo east");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
