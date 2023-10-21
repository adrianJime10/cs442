package visitorPlay.Util;

import visitorPlay.Util.MyElement;
import visitorPlay.Util.Element;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import visitorPlay.Util.FileProcessor;
import java.io.IOException;

public class MyArrayList implements Element{

	public static class Builder{

		private FileProcessor fileProcessor;

		public Builder(){
		}

		public Builder withFileProcessor(FileProcessor fileProcessorIn){
			fileProcessor = fileProcessorIn;
			return this;
		}

		public Element build(){
			String line = null;
		        try{
				line = fileProcessor.poll();
			}catch(IOException e){
				e.printStackTrace();
			}
			String[] sentences;
			List<Element> newArrayList = new ArrayList();
			while(line != null){
				sentences = line.split("(?<=[.!?])\\s*");
				try{
					line = fileProcessor.poll();
				}catch(IOException e){
					e.printStackTrace();
				}
				for(String sentence : sentences){
					Element element = new MyElement(sentence);
					newArrayList.add(element);
				}
			}
			try{
				fileProcessor.close();
			}catch(IOException e){
				System.out.println("Error closing file processor. Shutting down");
				System.exit(1);
			}
			return new MyArrayList(newArrayList);
		}

	}

	private List<Element> myArrayList;

	public MyArrayList(List<Element> listIn){
		myArrayList = listIn;
	}

	public Iterator<Element> getIterator(){
		return myArrayList.iterator();
	}

	@Override
	public void accept(Visitor visitor){
		for(Element element : myArrayList){
			element.accept(visitor);
		}
	}

	@Override
	public int hashCode(){
		return 3;
	}

	@Override
	public String toString(){
		return "MyArrayList";
	}

}
