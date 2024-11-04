# gcc_linux

## install

```shell
# GCCインストール
sudo apt install build-essential

# アセンブラインストール
sudo apt install nasm

# binutilsインストール　※readelfコマンド
sudo apt install binutils
```

## build

```shell
gcc -o hello hello.c

nasm -f elf64 ./hello.asm 
ld -o hello ./hello.o
readelf -l hello
```

## execute

```shell
./hello

./hello
```

## 調査

```shell
readelf -l hello

file hello

hexdump -C hello

objdump -d hello -M intel
```
