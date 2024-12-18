%include "io.inc"

section .date
a: dd 6
b: dd 4
c: dd 4
d: dd 2
e: dd 8

section .text
global CMAIN
CMAIN:
    ;write your code here
    mov ebp,esp
    
    mov eax,[a]
    mov ebx,[b]
    mov ecx, 2
    
    mul ebx
    mul ecx
    mov ebx,eax
    
    mov eax,[c]
    mov ecx,[d]
    mov edx, 0
    div ecx
    add ebx,eax
    
    mov eax,[e]
    mov ecx,2
    mov edx,0
    div ecx
    sub ebx,eax
    PRINT_DEC 4,ebx
    NEWLINE
    
    mov eax,ebx
    mov ebx,2
    mov edx,0
    div ebx
    PRINT_DEC 4,edx
    NEWLINE
    
    
    
    
    
    xor eax, eax
    ret