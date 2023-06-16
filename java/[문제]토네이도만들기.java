import java.util.ArrayList;
public class 토네이도 {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ArrayList<String> xPosionList = new ArrayList<String>();
        ArrayList<String> yPosionList = new ArrayList<String>();
        ArrayList<String> xRandomList = new ArrayList<String>(); 
         ArrayList<String> yRandomList = new ArrayList<String>();

         String money = "10";
    
        int value = Integer.parseInt(money)/4 +1;
        int mod = (Integer.parseInt(money)-1)%4;
        if(mod > 0 && mod<3) {
            value+=1;
        }
        for(int k=0;k<value;k++)
        {
            String [] position = new String[2];
            if(k==0) {  // 시작점은 중심에서 시작
                position[0] = "0";
                position[1] = "0";
            }else {
                for(int i = 0; i < position.length; i++) {
                    position[i] = String.format("%.5f", Math.random() *500);    //x,y축 max 값을 500으로 설정하고, 소숫점 5자리까지로 설정함
                    for (int j = 0; j < i; j++ ) {
                        if(position[i] == position[j]) {
                            i--;
                            break;
                        }
                    }
                }
            }
            xPosionList.add(position[0]);
            yPosionList.add(position[1]);
        }
        
        
        for(int i =0; i< xPosionList.size(); i++) {
            if(i == 0) {
                xRandomList.add(xPosionList.get(i));
                yRandomList.add(yPosionList.get(i));
            }else {
                if(i == xPosionList.size()-1) {
                    //  mod 값 있는 경우
                    if(mod > 0) {   // mod =1
                        xRandomList.add(xPosionList.get(i));
                        yRandomList.add(yPosionList.get(i));
                        //mod 갯수만큼
                        if(mod > 1) {   // mod =2
                            xRandomList.add(String.valueOf(Double.parseDouble(yPosionList.get(i)) *-1));
                            yRandomList.add(xPosionList.get(i));
                        }
                        if(mod > 2) {   // mod =3
                            xRandomList.add(String.valueOf(Double.parseDouble(xPosionList.get(i)) *-1));
                            yRandomList.add(String.valueOf(Double.parseDouble(yPosionList.get(i)) *-1)); 
                        }                    
                    }else { //mod 값 없는 경우
                        xRandomList.add(xPosionList.get(i));
                        yRandomList.add(yPosionList.get(i));
                        xRandomList.add(String.valueOf(Double.parseDouble(yPosionList.get(i)) *-1));
                        yRandomList.add(xPosionList.get(i));
                        xRandomList.add(String.valueOf(Double.parseDouble(xPosionList.get(i)) *-1));
                        yRandomList.add(String.valueOf(Double.parseDouble(yPosionList.get(i)) *-1));
                        xRandomList.add(yPosionList.get(i));
                        yRandomList.add(String.valueOf(Double.parseDouble(xPosionList.get(i)) *-1));
                    }
                }else {
                    xRandomList.add(xPosionList.get(i));
                    yRandomList.add(yPosionList.get(i));
                    xRandomList.add(String.valueOf(Double.parseDouble(yPosionList.get(i)) *-1));
                    yRandomList.add(xPosionList.get(i));
                    xRandomList.add(String.valueOf(Double.parseDouble(xPosionList.get(i)) *-1));
                    yRandomList.add(String.valueOf(Double.parseDouble(yPosionList.get(i)) *-1));
                    xRandomList.add(yPosionList.get(i));
                    yRandomList.add(String.valueOf(Double.parseDouble(xPosionList.get(i)) *-1));
                }
            }
        }
        //결과출력 거꾸로 //딱 10개까지만 결과나옴 사실 위에서 더 가능한데 결과만 그렇게 만듬.
        int parseMoney = (Integer.parseInt(money)/2)-2;
        for(; parseMoney > -1; parseMoney--){
            System.out.println(xPosionList.get(parseMoney)); 
            System.out.println(yPosionList.get(parseMoney)); 
        }
    }
}