package com.brosedda.mymediary.data

import org.mindrot.jbcrypt.BCrypt

/**
 * Encrypt a string with a log10 salt
 * @return Encrypted string
 */
fun String.encrypt(): String = BCrypt.hashpw(this, BCrypt.gensalt(10))

/**
 * Check if a string is similar to a hashed one
 * @param hashed Hashed string to compare to
 * @return are the strings similar ?
 */
fun String.check(hashed: String) = BCrypt.checkpw(this, hashed)

/**
 * Verify if a hashed string is similar to a plain one
 * @param plain plain string to compare to
 * @return are the two string similar
 */
fun String.verify(plain: String) = BCrypt.checkpw(plain, this)