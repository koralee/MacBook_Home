package study;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Assignment extends JFrame {

	Font f1 = new Font("궁서체", Font.ITALIC, 20);

	private JTextField count1 = new JTextField(14); // text
	private JTextField tmemo1 = new JTextField(26); // text
	private JTextField count2 = new JTextField(14); // text
	private JTextField tmemo2 = new JTextField(26); // text
	private JTextArea view1 = new JTextArea(20, 15); // text
	private JTextArea view2 = new JTextArea(20, 15); // text

	// ====================================================
	// 상단 메뉴바 멤버필드
	private MenuBar mb = new MenuBar();
	private Menu file = new Menu("기능(F)");
	private Menu edit = new Menu("계산기(c)");
	private Menu help = new Menu("도움말(H)");
	private MenuItem fnew = new MenuItem("저장하기(S)");
	private MenuItem fopen = new MenuItem("불러오기(L)");
	// ====================================================
	// 이미지 작업 멤버필드
	private Icon icon1 = new ImageIcon("/Users/honge/Documents/cat.jpeg");
	private JLabel jlb1 = new JLabel(icon1, JLabel.CENTER);
	// 이미지 작업 멤버필드 끝
	// ====================================================
	// 첫 번째 라벨 작업
	private JLabel incount = new JLabel("수입");
	private JLabel Money1 = new JLabel("금액 :");
	private JLabel outcount = new JLabel("지출");
	private JLabel Money2 = new JLabel("금액 :");
	private JLabel Memo1 = new JLabel("Memo");
	private JLabel Memo2 = new JLabel("Memo");
	private JLabel remainCount = new JLabel("현재 금액 :", JLabel.CENTER);

	// 첫 번째 라벨 작업 끝
	// 두 번째 라벨 작업
	private Choice co1 = new Choice();
	private Choice co2 = new Choice();
	// 첫 번째 라벨 작업 끝
	// ====================================================
	// 아래 버튼 작업
	private JButton dbt1 = new JButton("INCOME");
	private JButton dbt2 = new JButton("EXPENSE");
	// 버튼 작업 끝

	public Assignment() {
		super("수홍's 가계부");
		setLayout(new BorderLayout());
		MenuBar(); // 메뉴바 셋팅
		setFont(); // 폰트설정
		Layout();
		
		Exit();// 종료 및 설정
	}

	//Layout 셋팅
	public void Layout() {
		JPanel ImagePanel = new JPanel(new FlowLayout());// 이미지
		ImagePanel.add(jlb1);
		add(ImagePanel);


		// 왼쪽 레이아웃
		JPanel totalLeft = new JPanel(new BorderLayout());
		totalLeft.add("North", ImagePanel);
		totalLeft.add("Center", input()); // 인풋 여기서 추가
		totalLeft.add("South", button());// 버튼 여기서 추가
		add("Center", totalLeft);

		// 오른쪽 레이아웃
		JPanel text = new JPanel(new GridLayout(1, 2));
		JScrollPane jsp1 = new JScrollPane(view1,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		view1.setEditable(false);
		text.add(jsp1);

		JScrollPane jsp2 = new JScrollPane(view2,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		view2.setEditable(false);
		text.add(jsp2);

		JPanel zzz = new JPanel(new BorderLayout());
		zzz.add("Center", text);
		zzz.add("South", remainCount);

		JPanel full = new JPanel(new FlowLayout());
		full.add(totalLeft);
		full.add(zzz);
		add("East", full);
	}
		
	// 입력창 셋팅
	public JPanel input() {
		JPanel All = new JPanel(new GridLayout(4, 1));
		JPanel FirstPanelLine = new JPanel(new FlowLayout(FlowLayout.LEFT));
		FirstPanelLine.add(incount);
		co1.add("용돈");
		co1.add("월급");
		co1.add("비상금");
		FirstPanelLine.add(co1);
		FirstPanelLine.add(Money1);
		FirstPanelLine.add(count1);
		All.add(FirstPanelLine);

		JPanel SecondPanelLine = new JPanel(new FlowLayout(FlowLayout.LEFT));
		SecondPanelLine.add(Memo1);
		SecondPanelLine.add(tmemo1);
		All.add(SecondPanelLine);

		JPanel ThirdPanelLine = new JPanel(new FlowLayout(FlowLayout.LEFT));
		ThirdPanelLine.add(outcount);
		co2.add("문화생");
		co2.add("식비");
		co2.add("교통비");
		ThirdPanelLine.add(co2);
		ThirdPanelLine.add(Money2);
		ThirdPanelLine.add(count2);
		All.add(ThirdPanelLine);

		JPanel fourthPanelLine = new JPanel(new FlowLayout(FlowLayout.LEFT));
		fourthPanelLine.add(Memo2);
		fourthPanelLine.add(tmemo2);
		All.add(fourthPanelLine);
		add(All);
		return All;
	}

	// 버튼 셋팅
	public JPanel button() {
		JPanel Button = new JPanel(new FlowLayout());
		Button.add(dbt1);
		Button.add(dbt2);
		add("South", Button);
		return Button;
	}

	// 메뉴바 셋팅
	public void MenuBar() {
		setMenuBar(mb);
		mb.add(file);
		mb.add(edit);
		mb.add(help);
		file.add(fnew);
		file.addSeparator();
		file.add(fopen);
	}

	// 폰트 셋팅
	public void setFont() {
		incount.setFont(f1);
		Money1.setFont(f1);
		outcount.setFont(f1);
		Money2.setFont(f1);
		Memo1.setFont(f1);
		Memo2.setFont(f1);
	}

	// 종료버튼 메소드
	public void Exit() {
		pack(); // 사이즈 자동 조정
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Assignment(); // 생성자만 호출해서 실행!
	}
}