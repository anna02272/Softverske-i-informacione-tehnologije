%include "io.inc"

section .date
a: dd 9
b: dd 11
c: dd 8
d: dd 10
e: dd 3

section .text
global CMAIN
CMAIN:
    ;write your code here
    
    mov eax,[a]
    mov ebx,[b]
    add eax, ebx
    
    mov ecx, 3
    mul ecx
    mov ebx,eax
    
    mov eax,[c]
    mov ecx,[d]
    mul ecx
    add ebx,eax
    
    mov eax,ebx
    mov ebx,[e]
    mov edx,0
    div ebx
    PRINT_DEC 4,eax
    NEWLINE
    
    xor eax, eax
    ret