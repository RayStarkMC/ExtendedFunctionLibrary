/**
 * 関数を再帰的に記述するための関数型インターフェースを提供します。
 *
 * <p>本パッケージは以下の2種類の型で構成されています。
 * <ul>
 *     <li>TailCallインターフェース、及びそのプリミティブ特殊型からなる末尾再帰関数を最適化するインターフェース。</li>
 *     <li>{@link raystark.eflib.function}パッケージ下の関数を末尾再帰関数として、ラムダやメソッド参照で記述するための関数型インターフェース。</li>
 * </ul>
 *
 * <p>末尾再帰関数とは再帰関数の内、再帰呼び出しがreturn文にのみ現れる再帰関数です。末尾再帰関数には再帰呼び出しをループに展開できるという特徴があります。
 * 通常関数を再帰的に呼び出す場合は呼び出し元のどの時点に戻るのか記憶するためにコールスタックを1つ消費します。
 * 再帰呼び出しが深くなるとコールスタックの消費によりスタック領域が圧迫され、
 * メモリが不足した時点で{@link java.lang.StackOverflowError}がスローされます。
 * 末尾再帰関数の場合自身を再帰的に呼び出す時点で呼び出し元の処理は終了しているため、関数の終了後に元の関数に戻る必要がありません。
 * 従ってある末尾再帰関数呼び出しのステップ終了時に呼び出し元の記憶を保持せず、次に呼び出す関数のみを保持することでループに展開することができます。
 * このとき次に評価する関数はスタック領域ではなくヒープ領域に記憶されます。最適化された末尾再帰関数では呼び出しごとに以前の関数への参照が無くなるため、
 * それらは残りの関数の評価中にGCに回収される可能性があります。このため、通常の再帰関数よりも少ないメモリ消費で関数を評価出来る可能性があります。
 *
 * <p>Java言語では末尾再帰関数を通常通り定義した場合コンパイラによる最適化は行われず、呼び出しごとにコールスタックを消費します。
 * しかし、末尾再帰関数のループ展開を手動で行うことでコンパイラに依存せず最適化することができます。
 * TailCallインターフェース、及びその特殊型は末尾再帰呼び出しをラップするためのメソッドを提供します。
 * これらを使って記述された末尾再帰関数は自動で最適化されます。
 * 末尾再帰関数の正しい実装方法は{@link raystark.eflib.function.recursive.TailCall}を参照してください。
 *
 * <p>本パッケージ下の頭文字R: Recursiveで始まるインターフェースは末尾再帰関数をラムダ及びメソッド参照で記述するための関数型インターフェースです。
 * 各関数は{@link raystark.eflib.function}下のRを除く名前が一致する関数と対応しています。
 * 各インターフェースは再帰関数の記述のみを目的としており、部分適用や合成関数の作成などはサポートされていません。
 * 各インターフェースにはラムダ式用とメソッド参照用の2種類のofメソッドが定義されています。
 * 匿名クラスと違ってラムダ式ではthisが自身を参照しないため、各関数は自身の参照を持つselfを引数に取ります。
 * メソッド参照により関数を定義するためのインターフェースは各関数のインナーインターフェースとして定義されています。
 *
 * <p>以下に自然数nの階乗を求める関数をラムダ式で定義されたlambdaFact、
 * 及び再帰的に定義されたメソッドfactを参照して定義されたmethodFactのコード例を示します。
 * <pre>{@code
 *      public class SomeClass {
 *          public static void main(String[] args) {
 *              F2<BigInteger, BigInteger, BigInteger> factBase1 = RF2.of((t0, n, self) -> {
 *                  if(n.compareTo(ONE) < 0) return TailCall.complete(t0);
 *
 *                  return TailCall.call(() -> self.apply(t0.multiply(n), n.subtract(ONE)));
 *              });
 *
 *              F1<BigInteger, BigInteger> lambdaFact = factBase1.apply(ONE);
 *              F1<BigInteger, BigInteger> methodFact = RF2.of(SomeClass::fact).apply(ONE);
 *
 *              System.out.println(lambdaFact.apply(valueOf(5))); // print 120
 *              System.out.println(methodFact.apply(valueOf(5))); // print 120
 *          }
 *
 *          public static TailCall<BigInteger> fact(BigInteger t0, BigInteger n) {
 *              if(n.compareTo(ONE) < 0) return TailCall.complete(t0);
 *
 *              return TailCall.call(() -> fact(t0.multiply(n), n.subtract(ONE)));
 *          }
 *      }
 * }</pre>
 *
 * <p>各階乗関数にBigInteger.ONEを部分適用をしている点に注意してください。ofメソッドは{@link raystark.eflib.function}下の関数を返すため、
 * 部分適用を始めとした様々なdefaultメソッドを利用することができます。
 *
 * @see raystark.eflib.function.recursive.TailCall
 */
package raystark.eflib.function.recursive;