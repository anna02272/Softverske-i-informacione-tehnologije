%include "io.inc"

section .data
x: dd 0 
y: dd 0
z: dd 0
d: dd 0

section .text
global CMAIN
CMAIN:

    GET_DEC 4, [x]
    GET_DEC 4, [y]
    GET_DEC 4, [z]
    
    mov eax, [x]
    mov ebx, [y]
    mov ecx, [z]
    
    add eax, ebx
    add ecx, eax
    mov [d], ecx
    
    PRINT_DEC 4, [d]
    
    xor eax, eax
    ret