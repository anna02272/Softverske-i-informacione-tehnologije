%include "io.inc"
section .data

x: dd 0 
y: dd 0 
z: dd 0 
 
section .text
global CMAIN
CMAIN:

    GET_DEC 4, [x]
    GET_DEC 4, [y]
    
    mov eax, [x]
    mov eax, [y]
    
    add eax, ecx
    mov [z], eax
    
    PRINT_DEC 4, [z]
    
    xor eax, eax
    ret