/**
 * Visitorパターンの実装補助を行う型を提供します。
 *
 * <p>Visitorパターンはデザインパターンの1つです。
 * Visitorパターンを用いることで既知のデータ構造を変更せずそれら対する処理の拡張が出来るようになりますが、
 * 一方で既知の処理を変更せずそれらに対するデータ構造を拡張することは難しくなります。
 * Visitorパターンは直和型に対するパターンマッチと実質的に等価です。
 *
 * <p>本パッケージではVisitorパターンを以下の3種類の型に分けて抽象化します。
 * <ul>
 *     <li>Acceptor: データ構造を表します。Definitionを渡されると自身をDefinitionの適切なメソッドにディスパッチします。</li>
 *     <li>Definition: データ構造に対する処理の定義を表します。DefinedVisitorが保持しており、必要に応じてAcceptorに渡されます。</li>
 *     <li>Dispatcher: Acceptorに対するDefinitionのディスパッチャーを表します。Visitorのクライアントから見ると関数のように振舞います。</li>
 * </ul>
 * AcceptorとDefinitionについてはそれぞれacceptor, definitionパッケージを参照してください。
 *
 * <p>Dispatcherの命名は引数の個数や自身の扱う直和型に対する派生型の個数を表しています。対応は以下の通りです。
 * <ul>
 *     <li>Mono, Di: applyメソッドの引数の個数はギリシャ数字で表されます。Dispatcherの直前に付きます。現在2変数までが定義されています。</li>
 *     <li>2, 3: 扱う直和型に対する派生型の個数はアラビア数字で表されます。Dispatcherの直後に付きます。</li>
 *     <li>I, Abstract: インターフェース、又は骨格実装を表す型名の接頭辞です。無印の場合標準の不変な実装を表します。</li>
 * </ul>
 *
 * <p>Dispatcherの実装者は目的に応じて利用する型を選択できます。その際は以下の順に検討する事をお勧めします。
 * <ol>
 *     <li>
 *         接頭辞が無印のDispatcherは最も単純で不変な実装です。不変性を破る継承を禁止するためにclassで定義されています。
 *         インスタンス生成用のファクトリメソッドが定義されており、ラムダ式を用いて簡単に実装することができます。
 *     </li>
 *     <li>
 *         AbstractDispatcherはIDispatcherの骨格実装です。実装者は演算の定義を表すdefinitionメソッドを実装します。
 *         例えばDefinitionが状態を保持するためにapplyメソッドの呼び出し毎に新たなdefinitionを生成する必要がある場合や、
 *         Definitionとして独自の型を利用したい場合、またDispatcherが状態を保持する場合に利用できます。
 *         AbstractDispatcherで実装されたメソッドはfinalで宣言されるため、オーバーライド出来ません。
 *     </li>
 *     <li>
 *         IDispatcherはDispatcherの基底となるインターフェースです。最大限の抽象化が必要な場合に利用できます。
 *         いくつかのメソッドはデフォルトで実装されていますが、実装者は自由にオーバーライド出来ます。
 *     </li>
 * </ol>
 *
 * <p>Dispatcherの利用者はDispatcherを直和型の規定型を引数にとる関数のように利用できます。
 * 例えばOption<Integer>に対して値をインクリメントしたOptionを返すDispatcherは次のように利用できます。
 * <pre>{@code
 *    var incrementer = Option.<Integer, Option<Integer>>monoDispatcher(builder -> builder
 *      .type1(some -> Some.of(some.get()+1))
 *      .type2(none -> None.of())
 *    );
 *
 *    var some1 = Some.of(1);
 *    var some2 = incrementer.apply(some1);
 * }</pre>
 * 合成関数が必要な場合はasFメソッドにより対応するラムダインスタンスを生成できます。
 */
package raystark.eflib.visitor;