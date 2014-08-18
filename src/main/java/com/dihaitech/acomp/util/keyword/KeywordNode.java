package com.dihaitech.acomp.util.keyword;

import java.util.HashMap;
import java.util.Map;

/**
 * 关键词节点
 * 
 * @author qiusen
 * 
 */
public class KeywordNode {

	public KeywordNode(char key) {
		this.key = key;
	}

	/**
	 * 单个字
	 */
	private char key;
	
	/**
	 * 是否为叶子节点，关键词结束
	 */
	private boolean isLeaf;

	/**
	 * 子集
	 */
	private Map<Character, KeywordNode> childrenMap;
	
	/**
	 * 词
	 */
	private String word;

	public char getKey() {
		return key;
	}

	public void setKey(char key) {
		this.key = key;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Map<Character, KeywordNode> getChildrenMap() {
		
		if(childrenMap == null){
			this.childrenMap = new HashMap<Character, KeywordNode>();
		}
		
		return childrenMap;
	}

	public void setChildrenMap(Map<Character, KeywordNode> childrenMap) {
		this.childrenMap = childrenMap;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	
}
