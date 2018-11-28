package castle;

import java.util.HashMap;
import java.util.Scanner;



public class Game {
	private Room currentRoom;
	private HashMap<String, Handler> handlers = new HashMap<String, Handler>();

	public Game() {
		handlers.put("bye", new HandlerBye(this));
		handlers.put("help", new HandlerHelp(this));
		handlers.put("go", new HandlerGo(this));
		createRooms();
	}

	private void createRooms() {
		Room outside, lobby, pub, study, bedroom;

		// 鍒堕�犳埧闂�
		outside = new Room("鍩庡牎澶�");
		lobby = new Room("澶у爞");
		pub = new Room("灏忛厭鍚�");
		study = new Room("涔︽埧");
		bedroom = new Room("鍗у");

		// 鍒濆鍖栨埧闂寸殑鍑哄彛

		outside.setExits("east", lobby);
		outside.setExits("south", study);
		outside.setExits("west", pub);
		lobby.setExits("north", outside);
		lobby.setExits("up", pub);
		pub.setExits("down", lobby);
		pub.setExits("south", outside);
		study.setExits("east", outside);
		study.setExits("south", bedroom);
		bedroom.setExits("north", study);

		currentRoom = outside; // 浠庡煄鍫￠棬澶栧紑濮�
	}

	private void printWelcome() {
		System.out.println();
		System.out.println("娆㈣繋鏉ュ埌鍩庡牎锛�");
		System.out.println("杩欐槸涓�涓秴绾ф棤鑱婄殑娓告垙銆�");
		System.out.println("濡傛灉闇�瑕佸府鍔╋紝璇疯緭鍏� 'help' 銆�");
		System.out.println();
		showPrompt();
		System.out.println();
	}

	// 浠ヤ笅涓虹敤鎴峰懡浠�


	public void goRoom(String direction) {
		Room nextRoom = null;
		nextRoom = currentRoom.getExit(direction);
		if (nextRoom == null) {
			System.out.println("閭ｉ噷娌℃湁闂紒");
		} else {
			currentRoom = nextRoom;
			showPrompt();

		}
	}

	private void showPrompt() {
		System.out.println("浣犲湪" + currentRoom);
		System.out.print("鍑哄彛鏈�: ");
//       鏄剧ず淇℃柊鎴块棿鍑哄彛
		System.out.print(currentRoom.getRoomExitDes());
		System.out.println();
	}

	public  void play() {
		Scanner in = new Scanner(System.in);
		while (true) {
			String line = in.nextLine();
			String[] words = line.split(" ");
			Handler handler = handlers.get(words[0]);
			String value = "";
			if (words.length>1)
			{
				value = words[1];
			}
			
			if(handler != null) {
				handler.doCmd(value);
				if (handler.isBye()) {
					break;
				}				
			}
			}
		in.close();
		}
	

	

	public static void main(String[] args) {

		Game game = new Game();
		game.printWelcome();
		game.play();
		System.out.println("鎰熻阿鎮ㄧ殑鍏変复銆傚啀瑙侊紒");
		System.out.println("欢迎再回来");
	}

}
