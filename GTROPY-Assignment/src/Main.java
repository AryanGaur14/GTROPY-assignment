import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class Trie{
    Trie child[]=new Trie[127];
    boolean end;
}
class Dictionary{
    Trie root;
    public Dictionary(){
        root=new Trie();
    }

//  Time Complexity - O(n) || Space Complexity - O(n) where n is the number of character in String.
    public void insert(String str){
        Trie curr=root;
        int index;
        for(int i=0;i<str.length();i++){
            index=str.charAt(i);
            if(curr.child[index]==null){
                curr.child[index]=new Trie();
            }
            curr=curr.child[index];
        }
        curr.end=true;
    }

//Time Complexity - O(n) || Space Complexity - O(1) where n is the number of character in Key
    public String search(String key){
        Trie curr=root;
        int index;
        String SuggestedWord="";
        for(int i=0;i<key.length();i++){
            index=key.charAt(i);
            SuggestedWord=SuggestedWord+key.charAt(i);
            if(curr.child[index]==null){
                return suggestion(curr,SuggestedWord);
            }
            curr=curr.child[index];
        }
        if(curr.end){
            return "true";
        }
        else{
            return suggestion(curr,SuggestedWord);
        }
    }

//    Time Complexity - O(n2) || Space Complexity - O(n) where n is number of digit in Suggested Character
    public String suggestion(Trie curr,String SuggestedWord){
        if(curr.end){
            return SuggestedWord;
        }
        for(int i=0;i<curr.child.length;i++){
            if(curr.child[i]!=null){
                char c=(char)i;
               return suggestion(curr.child[i],SuggestedWord+c);
            }
        }
        return "No Suggestion Found!!";
    }

}
public class Main {
    public static void main(String[] args) {
        Dictionary dir=new Dictionary();
        Scanner sc=new Scanner(System.in);
        try {
            BufferedReader br = new BufferedReader(new FileReader("list.txt"));
            String str;

//Time Complexity - O(n2) || Space Complexity - O(n) where n is the number of character in String.
            while((str=br.readLine())!=null){
                dir.insert(str);
            }


//This portion of Code is Just For Input Where User can Search Any String.
//Start
            boolean flag=true;
            while(flag){
                System.out.println("Enter 0 for exit or 1 for searching");
                int choice=sc.nextInt();
                sc.nextLine();
                if(choice==0){
                    flag=false;
                }
                else {
                    System.out.println("Enter Your String");
                    String input = sc.nextLine();
                    String Word=dir.search(input);
                    if(Word=="true"){
                        System.out.println("Found "+input);
                    }
                    else{
                        System.out.println("Not Found! Suggested Word "+Word);
                    }
                }

            }
//End
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
}