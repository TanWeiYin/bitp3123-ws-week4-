
public class WordsCounter 
{
	public String Counter(String textReceive)
	{
		int words = textReceive.split("\\s+|,\\s*|\\.\\s*").length;
		
//		char ch[]= new char[textReceive.length()];  
//		
//		for(int i=0;i<textReceive.length();i++)  
//        {  
//            ch[i]= textReceive.charAt(i);  
//            
//            if( ((i>0) && (ch[i]!=' ') && (ch[i-1]==' ')) || ((ch[0]!=' ') && (i==0)))
//            {
//            	words++; 
//            }
//            	 
//        }  
		
		String result = Integer.toString(words);
		
		return result;
	}
}
