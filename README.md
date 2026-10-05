#Block and Stream Cipher Program. 
This project is a simple encryption and decryption program developed for CS356 (Systems and Security). It implements both a block cipher and a stream cipher and uses command-line arguments to specify the input file, output file, key file, and encryption/decryption mode.  
  
##Compilation and Usage:
Use the provided Makefile to compile the program. Once compiled, the program can be executed using command-line arguments.  
The program expects the following arguments:  

"java Cipher B|S input-file output-file key-file E|D". 

Where:  
B — Use the block cipher. 
S — Use the stream cipher. 
"input-file" — File containing the message to encrypt or decrypt. 
"output-file" — File where the resulting message will be written. 
"key-file" — File containing the encryption/decryption key. 
E — Encrypt the input. 
D — Decrypt the input. 

Example:  
To encrypt a message using the block cipher:  
java Cipher B b-e-input.txt b-e-output.txt b-e-key.txt E. 

After the program finishes, the output can be inspected using:  
hexdump -C b-e-output.txt. 

##Block Cipher Algorithm. 
The block cipher processes the message in 16-byte blocks.  
Before encryption, the program checks whether the message length is a multiple of 16 bytes. If it is not, the message is padded so that it can be divided into complete 16-byte blocks.  

The encryption process then operates on each 16-byte block using the provided key.  

During decryption, the process is reversed. The program decrypts each block using the key, removes the padding that was added during encryption, and writes the resulting plaintext to the output file.  

Block Cipher Process:  
Input Message -> Check Padding -> Pad -> Encrypt -> Output. 

Decryption follows the reverse process:  
Encrypted Message -> Decrypt Each Block - > Remove Padding -> Original Message. 

##Stream Cipher Algorithm. 
The stream cipher is simpler than the block cipher because it processes the message one byte at a time.  
Each byte of the message is XORed with a corresponding byte from the key. XOR is useful for this purpose because applying the same operation twice reverses the result:  

Message XOR Key = Ciphertext. 
Ciphertext XOR Key = Message. 

Therefore, the same operation can be used for both encryption and decryption.  

##What I Learned:  
This project introduced me to the fundamental concepts of cryptography, particularly the differences between block and stream ciphers and how keys are used to protect information.  

One of the most important concepts I learned was the importance of key security. The encryption algorithm itself is only part of the security of a cryptographic system—the key is what allows someone to encrypt or decrypt the protected information. If a key is lost, exposed, or stolen, an attacker may be able to access the information it protects.  

This project also helped me better understand how encryption works at the byte level, including concepts such as padding, XOR operations, block processing, and reversible encryption. Overall, it provided practical experience with applying cryptographic concepts in a working program.  
