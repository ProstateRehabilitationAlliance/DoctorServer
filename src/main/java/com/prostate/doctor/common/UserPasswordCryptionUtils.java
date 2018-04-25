//package com.prostate.doctor.common;
//
//
//import com.prostate.doctor.entity.Doctor;
//
//import java.util.UUID;
//
//public class UserPasswordCryptionUtils {
//
//	// MD5加密
//	public static Doctor md5Password(String username, String password) {
//		String salt = UUID.randomUUID().toString();
//		salt = salt.replace("-","");
//		String password_cipherText = new Md5Hash(password, username + salt, 2).toHex();
//		Doctor doctor = new Doctor();
//		doctor.setDoctorPassword(password_cipherText);
//		doctor.setSalt(salt);
//		doctor.setDoctorPhone(username);
//		return doctor;
//	}
//
//	// MD5密码校验
//	public static boolean checkMd5Password(String username, String password, String salt, String md5cipherText) {
//		String password_cipherText = new Md5Hash(password, username + salt, 2).toHex();
//		return md5cipherText.equals(password_cipherText);
//	}
//}
