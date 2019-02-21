package com.algorithm.lcs;

import java.util.Stack;

import com.algorithm.util.ArrayUtil;

/***
 * @description ��̬�滮 �������������
 * Longest common subsequence(LCS)
 * @author yanhengtao
 *
 */
public class LcsAl {

	/**
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String getLCS(String str1, String str2) {

		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();
		int[][] array = new int[str1.length() + 1][str2.length() + 1]; // �˴������̳���Ҫ���ַ������ȶ��1

		for (int j = 0; j < array[0].length; j++) { // ��0�е�j��ȫ����ֵΪ0
			array[0][j] = 0;
		}
		for (int i = 0; i < array.length; i++) { // ��i�е�0��ȫ����ֵΪ0
			array[i][0] = 0;
		}

		for (int m = 1; m < array.length; m++) { // ���ö�̬�滮�����鸳��ֵ
			for (int n = 1; n < array[m].length; n++) {
				if (s1[m - 1] == s2[n - 1]) {
					array[m][n] = array[m - 1][n - 1] + 1; // ��̬�滮��ʽһ
				} else {
					array[m][n] = max(array[m - 1][n], array[m][n - 1]); // ��̬�滮��ʽ��
				}
			}
		}
		ArrayUtil.printArray(array);

		Stack<Character> stack = new Stack<Character>();
		int i = str1.length() - 1;
		int j = str2.length() - 1;

		while ((i >= 0) && (j >= 0)) {
			if (s1[i] == s2[j]) { // �ַ����Ӻ�ʼ������������ȣ������ջ��
				stack.push(s1[i]);
				i--;
				j--;
			} else {
				// ����ַ������ַ���ͬ����������������ͬ���ַ�
				// ע�⣺���������Ҫ���ַ������ַ��ĸ�����1�����i��jҪ����1
				if (array[i + 1][j] > array[i][j + 1]) {
					j--;
				} else {
					i--;
				}
			}
		}

		// ��ӡ���ջ����������������Ĺ���������
		String result = "";
		while (!stack.isEmpty()) {
			result += stack.pop().toString();
		}
		return result;
	}

	/**
	 * @description �Ƚ�(a,b)��������ֵ
	 * @param a
	 * @param b
	 * @return
	 */
	public static int max(int a, int b) {
		return (a > b) ? a : b;
	}
}