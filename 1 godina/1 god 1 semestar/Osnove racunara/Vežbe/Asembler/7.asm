%include "io.inc"
section .data

a:dd 0
b:dd 0
c:dd 0
d:dd 0
e:dd 0
r:dd 0

section .text
global CMAIN
CMAIN:
    mov ebp, esp; for correct debugging
    GET_UDEC 4, [a]
    GET_UDEC 4, [b]
    GET_UDEC 4, [c]
    GET_UDEC 4, [d]
    GET_UDEC 4, [e]
    
    mov eax,[a]
    shl eax,1
    mul dword[b]
    mov ecx,eax
    
    mov eax,[c]
    mov edx,0
    div dword[d]
    add ecx,eax
    
    mov eax,[e]
    shr eax, 1
    sub ecx,eax
    mov [r],ecx
    
    shl ecx,31
    shr ecx,31
    
    PRINT_UDEC 4,[r]
    NEWLINE
    PRINT_UDEC 4,ecx
    
    xor eax, eax
    ret