# gcc_linux

## install

```
# GCCインストール
sudo apt install build-essential

# アセンブラインストール
sudo apt install nasm
```

## build

```
gcc -o hello hello.c

nasm -f elf64 ./hello.asm 
ld -o hello ./hello.o
```