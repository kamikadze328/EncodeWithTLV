# EncodeWithTLV
В стандарте BER для нотации ASN1 используется кодирование значений по принципу TLV(Tag-Length-Value), при этом длина кодируется по следующим правилам:  
Length octets: There are two forms: short (for lengths between 0 and 127), and long definite (for lengths between 0 and 2^1008 -1).  
+ Short form. One octet. Bit 8 has value "0" and bits 7-1 give the length.  
+	Long form. Two to 127 octets. Bit 8 of first octet has value "1" and bits 7-1 give the number of additional length octets. Second and following octets give the length, base 256, most significant digit first.
  
  
+JUnit
