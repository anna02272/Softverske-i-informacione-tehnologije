%include "io.inc"
section .data
y:dd 0
section .text
global CMAIN
CMAIN:
    ;write your code here
    GET_DEC 4,EAX
    cmp eax,3
    jge plus_tri
    sub eax,1
    jmp kraj
    
plus_tri:
    add eax,3
kraj:
    mov [y], eax
    PRINT_DEC 4,EAX
    xor eax, eax
    ret