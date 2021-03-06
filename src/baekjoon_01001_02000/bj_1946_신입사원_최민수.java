package baekjoon_01001_02000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

//[실버 1] 신입 사원
//https://www.acmicpc.net/problem/1946
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_1946_신입사원_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_1946"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		//테케
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			//지원자의 숫자 N 1~10만
			int N = Integer.parseInt(br.readLine());

			//서류 점수, 면접 성적
			Person p[] = new Person[N];
			for (int j = 0; j < N; j++) {
				StringTokenizer	st = new StringTokenizer(br.readLine());
				int temp1 = Integer.parseInt(st.nextToken());
				int temp2 = Integer.parseInt(st.nextToken());
				p[j] = new Person(temp1, temp2);
			}
			
			//comparator를 활용한 오름차순 정렬
			Arrays.sort(p, new Comparator<Person>() {
				@Override
				public int compare(Person o1, Person o2) {
					return Integer.compare(o1.paperScore, o2.paperScore);
				}			
			});
			
//			System.out.println(p[0].paperScore);
//			System.out.println(p[1].paperScore);
//			System.out.println(p[2].paperScore);
//			System.out.println(p[3].paperScore);
			
			//다른 모든 지원자와 비교했을 때 서류심사 성적과 면접시험 성적 중
			//적어도 하나가 다른 지원자보다 떨어지지 않는 자만 선발한다
			
			//어떤 지원자 A의 성적이 다른 어떤 지원자 B의 성적에 비해
			//서류 심사 결과와 면접 성적이 모두 떨어진다면 A는 결코 선발되지 않는다.
			
			//최고 순위 기록
			int convBest = p[0].convScore;
			
			int answer = N;
			//서류 점수는 오름차순으로 정렬해놨으니 비교할 필요 없고
			//면접 점수를 비교하는데, 면접점수는 최고 순위를 갱신하면서 비교한다.
			for (int j = 1; j < N; j++) {
//				System.out.println(p[j].convScore);
				if(p[j].convScore > convBest) { // 1>2
					answer--;
				}
				else convBest = p[j].convScore;
			}

			//각 테스트 케이스에 대해서
			//선발할 수 있는 신입사원의 최대 인원수를 한 줄에 하나씩 출력
			System.out.println(answer);
		}
		
		br.close();
	}
	
}

class Person{
	int paperScore;
	int convScore;
	
	public Person(int paperScore, int convScore) {
		super();
		this.paperScore = paperScore;
		this.convScore = convScore;
	}	
}
