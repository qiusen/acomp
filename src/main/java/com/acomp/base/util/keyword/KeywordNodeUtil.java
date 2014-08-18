package com.acomp.base.util.keyword;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 关键词工具类
 * @author qiusen
 *
 */
public class KeywordNodeUtil {

	/**
	 * 关键词树
	 */
	public static Map<Character, KeywordNode> keywordNodeMap = new HashMap<Character, KeywordNode>();
	
	
	/**
	 * 初始化关键词树
	 * 
	 * @param keywordList
	 * @return
	 */
	public static Map<Character, KeywordNode> initKeywordNodeTreeMap(List<String> keywordList){
		
		
		if(keywordList!=null && keywordList.size()>0){
			String keywordTemp = null;
			for(int i=0;i<keywordList.size();i++){
				keywordTemp = keywordList.get(i);
				
				addKeywordToTree(keywordNodeMap, keywordTemp, keywordTemp);
			}
		}
		
		
		
		return keywordNodeMap;
	}
	
	/**
	 * 打印树
	 * @param keywordNodeMap
	 */
	private static void printKeywordNodeTreeMap(Map<Character, KeywordNode> keywordNodeMap){
		Iterator<Character> it = null;
		
		it = keywordNodeMap.keySet().iterator();
		while(it.hasNext()){
			char key = it.next();
			KeywordNode keywordNode = keywordNodeMap.get(key);
			System.out.println(key + "  " + keywordNode.isLeaf() + "  " + keywordNode.getChildrenMap().size() + "  " + keywordNode.getWord());
			if(keywordNode.getChildrenMap().size()>0){
				printKeywordNodeTreeMap(keywordNode.getChildrenMap());
			}
		}
	}
	

	/**
	 * 把数据加到树中
	 * @param keywordNodeMap
	 * @param keyword
	 */
	private static void addKeywordToTree(Map<Character, KeywordNode> keywordNodeMap, String keyword, String word){
		
		char[] keywordChars = keyword.trim().toCharArray();
		
		KeywordNode keywordNode = null;
		char key = keywordChars[0];
			
			//含有此节点，递归走下一字符
			if(keywordNodeMap.containsKey(key)){
				keywordNode = keywordNodeMap.get(key);
				
				if(keywordChars.length>1){
					addKeywordToTree(keywordNode.getChildrenMap(), new String(keywordChars, 1, keywordChars.length-1), word);
				}else{	//关键词结束
					keywordNode.setLeaf(true);
					keywordNode.setWord(word);
				}
				
			}else{	//没有此节点
				keywordNode = new KeywordNode(keywordChars[0]);
				if(keywordChars.length>1){
					keywordNodeMap.put(key, keywordNode);
					addKeywordToTree(keywordNode.getChildrenMap(), new String(keywordChars, 1, keywordChars.length-1), word);
				}else{	//关键词结束
					keywordNode.setLeaf(true);
					keywordNode.setWord(word);
					keywordNodeMap.put(key, keywordNode);
				}
			}
			
		
	}
	
	
	/**
	 * 查找关键词
	 * @param content
	 * @return
	 */
	public static List<String> findKeywordFromContent(String content){
		List<String> keywordList = new ArrayList<String>();
		
		char[] chars = content.toCharArray();
		
		KeywordNode keywordNode = null;
		for(int i=0;i<chars.length;i++){
			
			if(keywordNodeMap.containsKey(chars[i])){
				keywordNode = keywordNodeMap.get(chars[i]);
				traverseContent(keywordNode.getChildrenMap(), new String(chars, i+1, chars.length-i-1), keywordList);
			}
			keywordNode = keywordNodeMap.get(chars[i]);
			if(keywordNode!=null){
				
			}
		}
		
		return keywordList;
	}
	
	/**
	 * 递归分析的方法
	 * @param keywordNodeMap
	 * @param content
	 * @param keywordList
	 */
	private static void traverseContent(Map<Character, KeywordNode> keywordNodeMap, String content, List<String> keywordList){
		KeywordNode keywordNode = null;
		
		System.out.println(content);
		
		char[] chars = content.toCharArray();
		if(keywordNodeMap.containsKey(chars[0])){
			keywordNode = keywordNodeMap.get(chars[0]);
			if(keywordNode.isLeaf()){
				keywordList.add(keywordNode.getWord());
			}
			traverseContent(keywordNode.getChildrenMap(), new String(chars, 1, chars.length-1), keywordList);
		}
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<String> strList = new ArrayList<String>();
		
		strList.add("我爱你太阳");
		strList.add("我爱你");
		strList.add("我的太阳");
		strList.add("快乐");
		strList.add("我很嚣张");
		
		Date s = new Date();
		initKeywordNodeTreeMap(strList);
		Date e = new Date();
		long o = e.getTime() - s.getTime();
		System.out.println("====" + o);
		
		printKeywordNodeTreeMap(keywordNodeMap);
		
		String content = "啊，中国我爱你，你是我的的的的太阳哇，你知道我爱你太阳，我很快乐的说";
		Date s2 = new Date();
		List<String> keywordList = findKeywordFromContent(content);
		Date e2 = new Date();
		long o2 = e2.getTime() - s2.getTime();
		System.out.println("====" + o2);
		
		for(int i=0;i<keywordList.size();i++){
			System.out.println("包含keyword : " + keywordList.get(i));
		}
		
	}
}
