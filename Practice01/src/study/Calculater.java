package study;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Calculater extends JFrame implements ActionListener {

	private JTextField inputSpace;// 화면 구성
	private String num = "";// 숫자를 담을 변수
	private String SavePrevOperation = "";
	//계산 기능을 구현하기 위해ArrayList에 숫자와 연산기호를 하나씩 구분해서 저장
	private ArrayList<String> Store = new ArrayList<>();
	
	
//	private ArrayList<String> Buffered = new ArrayList<String>();
//
//	private boolean Trigger1 = false;
//	private boolean Trigger2 = false;
//	private String BNum = "";// B저장값
//	private int result;

	public Calculater() {
		setLayout(null);
		
		inputSpace = new JTextField();
		inputSpace.setEditable(false); // 수정 불가
		inputSpace.setBackground(Color.WHITE); //배경색
		inputSpace.setHorizontalAlignment(JTextField.RIGHT);// 정렬 위치
		inputSpace.setFont(new Font("Arial",Font.BOLD, 50));//폰트 설정
		inputSpace.setBounds(8, 10, 280, 70);//JtextField 크기 설정
		
		
		//버튼 생성 및 추가
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(5, 4, 10, 10));
		buttonPanel.setBounds(8, 90, 270, 235);
		
		String[] buttonName = { "→", "CE", "C", "=",
									"7", "8", "9", "+",
									"4", "5", "6", "-",
									"1", "2", "3", "x",
									"0","00", ".", "÷" };
		
		JButton[] buttons = new JButton[buttonName.length];
		for (int i = 0; i < buttonName.length; i++) {
			buttons[i] = new JButton(buttonName[i]);
			buttons[i].setFont(new Font("Arial", Font.BOLD, 30));
			
			if(buttonName[i]=="C"){
				buttons[i].setBackground(Color.RED);
			}else if(buttonName[i]=="→" || buttonName[i]=="CE"|| buttonName[i]=="="
			||buttonName[i]=="+" || buttonName[i]=="-"
			||buttonName[i]=="*" || buttonName[i]=="/") {
				buttons[i].setBackground(Color.GRAY);
			}else {
				buttons[i].setBackground(Color.WHITE);
			}
			buttons[i].setOpaque(true);
			buttons[i].setForeground(Color.black); //글자 색상
			buttons[i].addActionListener(this);//현재 클래스 안에있는 ActionLister사용을 의미
			buttonPanel.add(buttons[i]);
		}
		add(inputSpace); // JtextField 추가
		add(buttonPanel); // 버튼 패널 추가
		
		setTitle("계산");
		setSize(300, 370);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Calculater();
	}

	@Override
	// 버튼을 C와 = 버튼을 눌렀을 경우 Action을 설정
	public void actionPerformed(ActionEvent e) {
		String Operation = e.getActionCommand();
		if(Operation.equals("C")) {
			inputSpace.setText("");
		}else if(Operation.equals("=")) {
			String result  = Double.toString(calculate(inputSpace.getText()));
			//화면에 있는 값을 갖고와서 calculate에 넣어서 순차적으로 계산후 double을 string형태로
			//형변환하여 result에 저장!
			
			//그리고 계산된 모든 값을 다시 화면에 출력!
			inputSpace.setText(""+result);
			num = ""; // 값을 저장하던 변수도 다시 최기화!
			
			//지금 누른 버튼이 연산자라면
		}else if(Operation.equals("+")||Operation.equals("-")||
				Operation.equals("x")||Operation.equals("÷")){
			//음수인 경우를 대비
		if(inputSpace.getText().equals("")&& Operation.equals("-")) {
				inputSpace.setText(inputSpace.getText()+e.getActionCommand());
			}
			//그리고 계산식이 비어있지 않고 마지막입력값이 연산자가 아닌 경우
			else if(!inputSpace.getText().equals("")&&
					!SavePrevOperation.equals("+")&&!SavePrevOperation.equals("-")&&
					!SavePrevOperation.equals("x")&&!SavePrevOperation.equals("÷")) {
				//창에 있는 값+지금 누르는 버튼 값을 그대로 갖고 옴
				inputSpace.setText(inputSpace.getText()+e.getActionCommand());
			}
		}else {
			inputSpace.setText(inputSpace.getText()+e.getActionCommand());
		}
		//마지막에 누른 버튼 기억
		SavePrevOperation = e.getActionCommand();
	}
	
	//입력값을 ArrayList에 저장하는 메소드
	private void fullTextPrsding(String inputText) {
	 Store.clear();// ArrayList 가비지값 청소
	 for(int i = 0; i < inputText.length();i++) {
		 char ch = inputText.charAt(i);
		 
		 if(ch =='-' | ch =='+' | ch == 'x' | ch == '÷') {
			 Store.add(num);//숫자를 먼저 ArrayList에 저장
			 num = "";
			 //그리고 num을 다시 초기화! 이 부분의 개념이 생각보다 중요!
			 //연산자가 오기 전까지는 밑에 else에서 계속 숫자를 받아 더하다가
			 //연산자가 나오면 ArrayList에 저장하고 num은 초기화해버림!
			 //즉 연산자가 나오기 전의 num은 ArrayList에 저장되고
			 //  연산자가 나온 후의 num과는 다른 num이다!

			 //숫자를 ArrayList에 먼저 저장후 num을 초기화 후 연산자를 ArrayList의 순서에 맞게 저
			 Store.add(ch+"");
		 }else {
			 num = num + ch;
			 //ArrayList에 저장될 num //혹은 연산자라 눌린 후의 ArrayList에 저장될 num
		 }
	 }
	 //연산자가 눌린 후의 num을 ArrayList에 저장
	 Store.add(num);
	 
	 //연산자를 누르면 이전 값의 num은 ""(즉,null)값이 들어가져 오류!
	 //그래서 ArrayList내에서 ""를 삭제 해줌!
	 //안그러면 ""-3+6 = 이렇게 되서 계산이 안됨!
	 Store.remove("");
	}
	
	//입력값을 저장하는 메소드 실행후 저장된 리스트의 값으로 계산을 하는 메소드
	public double calculate(String inputText) {
		//입력값을 ArrayList에 저장하는 메소드 먼저 실행
		fullTextPrsding(inputText);  
		double prev = 0; // 이전 
		double Current = 0;// 현재 값 
		String mode = ""; // 연산기호 처리
		
		//우선순위 처리!
		for(int i= 0;i<Store.size();i++) {
			String s = Store.get(i);
			if(s.equals("+")){
				mode = "add";
			}else if(s.equals("-")) {
				mode = "sub";
			}else if(s.equals("x")) {
				mode = "mul";
			}else if(s.equals("÷")) {
				mode = "div";
			}else {
				if(mode.equals("mul")||mode.equals("div")&&
						!s.equals("+")&&!s.equals("-")&&
						!s.equals("x")&&!s.equals("÷")) {
					Double one = Double.parseDouble(Store.get(i-2));
					Double two = Double.parseDouble(Store.get(i));
					Double result = 0.0;
					if(mode.equals("mul")) {
						result = one * two;
					}else if(mode.equals("div")) {
						result = one / two;
					}
					Store.add(i+1, Double.toString(result));
					
					for(int j = 0; j<3;j++) {
						// i=4 - 2 = 2 즉, 인덱스 2를 계속 삭제
						// ArrayList는 앞에 인덱스가 삭제되면 뒤의 인덱스가 앞으로 당겨진다!
						// 즉 ArrayList의 크기는 이제 2이다! 
						Store.remove(i-2); //3번 반복하는 이유 ^ 
					}
					i-=2; //결과값이 저장되어있는 인덱스로 이동!!!
						  //하지만 ArrayList의 크기가 2인것지이
						  //현재 i값은 여전히 4이므로 4-2는 2
						  //즉! i값을 결과값이 생긴 인덱스로 이동
						  // 그래야 다름값이 인덱스2 뒤로 저장 될 수 있으므로
				}
			}
		}// 곱셈과 나눗셈을 먼저 계산함! 을 의미!
		
		
		for(String s : Store){ //Store내부에 있는 값을 자동으로,순차적으로 돌림
			if(s.equals("+")){
				mode = "add";
			}else if(s.equals("-")) {
				mode = "sub";
			}else {
				//연산자 기준으로 앞뒤로 저장된 숫자들을 Double로 형 변환!!! 후 저장
				//prev에는 초기화를 안해줘서 이전의 값이 계속 저장되어있다!!!
				//그러므로 =을 누르고 다시 연산자를 누르면 계속 계산이 가능!
				Current = Double.parseDouble(s);
				if(mode=="add")
				prev += Current; // 헷갈리면 풀어봐라 prev = Current(1) + Currnet(2);
				else if(mode =="sub"){
					prev -= Current;
				}else {
					// 연산자가 안들어오면 이전의 값이라고 생각하고 연산자 전으로 계속 넣어줘야함
					prev = Current; 
				}
			}
			// 6번째 자릿수에서 반올림/ 즉 소수점 5번째 까지 표현됨!
			prev = Math.round(prev * 100000)/100000.0; 
		}
		//마지막으로 값을 반환!
		return prev;
	}
	
 }








































