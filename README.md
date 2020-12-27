# Extended Function Library

## 概要
Extended Function Libraryは関数型プログラミングをサポートするライブラリです。  
様々な形式の関数型インターフェースが提供されている他、それらを利用したユーティリティを提供します。
以下、本ライブラリで実装されている機能です。  

### 関数型インターフェースのdefaultメソッドの拡張
* 合成関数の作成
* 引数の部分適用、及び遅延評価のサポート
* 引数の順序交換
* 関数の型変換
* ラムダやメソッド参照によるインスタンス生成

### 末尾再帰最適化と再帰ラムダ式
* 関数呼び出しのループ展開による末尾再帰関数の最適化
* 再帰関数をラムダ式で定義するためのインターフェース

### null利用の静的解析の補助
* [org.jetbrains:annotations]の利用によるnull利用の静的解析の補助

### コンテナ型
* 遅延初期化型
* 値の取得が型安全なOption型

## 利用方法
本ライブラリは[GitHubPackages]にて公開されています。  
ご利用の場合は以下のようにGitHubアカウントのトークン情報を設定してください。

```groovy
repositories {
    mavenCentral()
    maven {
        url = 'https://maven.pkg.github.com/raystarkmc/ExtendedFunctionLibrary'
        credentials {
            username = project.findProperty("github.username")
            password = project.findProperty("github.password")
        }
    }
}

dependencies {
    implementation 'raystark:extended_function_library:1.4.6'
}
```

## ライセンス
**ソースコード及びライブラリは[MITライセンス]で保護されています。**  
buildタスクで出力される全てのJarファイルにはLICENSEファイルが同封されています。

[org.jetbrains:annotations]: https://github.com/JetBrains/java-annotations
[MITライセンス]: https://opensource.org/licenses/mit-license.php
[GitHubPackages]: https://github.com/features/packages