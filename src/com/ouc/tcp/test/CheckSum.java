package com.ouc.tcp.test;

import java.util.zip.CRC32;

import com.ouc.tcp.message.TCP_HEADER;
import com.ouc.tcp.message.TCP_PACKET;

public class CheckSum {

	/* 计算TCP报文段校验和：只需校验TCP首部中的seq、ack和sum，以及TCP数据字段 */
	public static short computeChkSum(TCP_PACKET tcpPack) {
		int checkSum = 0;
		int seq = tcpPack.getTcpH().getTh_seq();
		int ack = tcpPack.getTcpH().getTh_ack();
		int sum = tcpPack.getTcpH().getTh_sum();

		checkSum = seq + ack + sum;
		int []data  = tcpPack.getTcpS().getData();

		for(int datam : data){
			checkSum += datam;
		}
		checkSum = ((checkSum>>16)& 0x0000ffff)+(checkSum& 0xffff0000);
		
		return (short) checkSum;
	}

}
