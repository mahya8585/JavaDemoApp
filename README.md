# Java Demo API

Spring Boot 3を使用したWeb APIデモンストレーションプロジェクト

## 概要

このプロジェクトは、Spring Boot 3（最新バージョン）を使用して構築されたRESTful Web APIシステムです。ユーザー管理機能を中心とした基本的なCRUD操作を提供します。

## 技術スタック

- **Java**: 17
- **Spring Boot**: 3.4.1
- **Spring Data JPA**: データベースアクセス
- **Spring Web**: RESTful API
- **Spring Boot Validation**: データ検証
- **Spring Boot Actuator**: ヘルスチェック
- **H2 Database**: インメモリデータベース（開発用）
- **Maven**: ビルドツール

## 主な機能

### 経費精算申請管理API（XML対応）
- 経費精算申請情報一覧取得（申請日降順、最新10件）
- テストデータ登録API（Azure SQL Database対応）
- XML形式でのレスポンス提供
- フレキシブル形式対応（XML/JSON両対応）

### システム管理API
- ヘルスチェック
- アプリケーション情報取得

## API エンドポイント

### 経費精算申請API（XML形式）
```
GET    /api/expense/applications             - 経費精算申請一覧取得（XML）
POST   /api/expense/data/insert              - テストデータ登録（XML）
GET    /api/expense/applications/flexible    - 形式指定対応版
POST   /api/expense/data/insert/flexible     - 形式指定対応版
```

### システムAPI
```
GET    /api/health                           - ヘルスチェック
GET    /api/health/info                      - アプリケーション情報
```

## クイックスタート

### 前提条件
- Java 17以上
- Maven 3.6以上

### 実行方法

1. プロジェクトのクローン
```bash
git clone <repository-url>
cd JavaDemoApp
```

2. アプリケーションの起動
```bash
mvn spring-boot:run
```

3. APIの確認
```bash
# ヘルスチェック
curl http://localhost:8080/api/health

# 経費精算申請一覧取得（XML形式）
curl -X GET http://localhost:8080/api/expense/applications \
  -H "Accept: application/xml"

# テストデータ登録（XML形式）
curl -X POST http://localhost:8080/api/expense/data/insert \
  -H "Accept: application/xml"

# 経費精算申請一覧取得（JSON形式）
curl -X GET "http://localhost:8080/api/expense/applications/flexible?format=json" \
  -H "Accept: application/json"
```

### H2データベースコンソール

開発時にデータベースの内容を確認できます：
- URL: http://localhost:8080/api/h2-console
- JDBC URL: `jdbc:h2:mem:testdb`
- ユーザー名: `sa`
- パスワード: `password`

## プロジェクト構造

```
src/main/java/com/example/javademoapi/
├── JavaDemoApiApplication.java    # メインアプリケーション
├── controller/                    # RESTコントローラー
│   ├── ExpenseController.java     # 経費精算申請API
│   └── HealthController.java      # ヘルスチェック
├── service/                       # ビジネスロジック
│   └── ExpenseApplicationService.java # 経費精算申請サービス
├── repository/                    # データアクセス層
│   └── ExpenseApplicationRepository.java # 経費精算申請リポジトリ
├── entity/                        # エンティティクラス
│   └── ExpenseApplication.java    # 経費精算申請エンティティ
├── dto/                          # データ転送オブジェクト
│   ├── ExpenseApplicationResponseDto.java # 経費精算申請レスポンスDTO
│   └── DatabaseOperationResultDto.java   # DB操作結果DTO
├── exception/                     # 例外クラス
│   ├── ResourceNotFoundException.java     # リソース未発見例外
│   ├── DuplicateResourceException.java    # 重複リソース例外
│   └── GlobalExceptionHandler.java        # グローバル例外ハンドラー
└── config/                       # 設定クラス
    └── DataLoader.java           # 初期データロード
```

## 設定

アプリケーションの設定は `src/main/resources/application.properties` で管理されています。

## テスト

```bash
# 全テスト実行
mvn test

# 特定のテストクラス実行
mvn test -Dtest=JavaDemoApiApplicationTests
```

## ビルド

```bash
# JARファイル作成
mvn clean package

# 作成されたJARファイルを実行
java -jar target/java-demo-api-0.0.1-SNAPSHOT.jar
```

## 開発のポイント

- **レイヤードアーキテクチャ**: Controller → Service → Repository の明確な分離
- **DTOパターン**: エンティティとAPIレスポンスの分離
- **XML対応**: Jackson XML形式でのレスポンス提供
- **フレキシブル形式**: XML/JSON両対応のエンドポイント
- **バリデーション**: Bean Validationを使用した入力検証
- **例外処理**: グローバル例外ハンドラーによる統一的なエラーレスポンス
- **Azure SQL Database対応**: クラウドデータベース接続サポート

## 拡張可能性

このプロジェクトは以下のような拡張が可能です：

- 認証・認可（Spring Security）
- 外部データベース接続（MySQL、PostgreSQL、Azure SQL Database等）
- キャッシュ機能（Redis）
- API文書化（OpenAPI/Swagger）
- ロギング強化
- メトリクス収集
- Docker化
- Azure App Service部署
- Azure Functions統合
- Azure Service Bus連携

## ライセンス

このプロジェクトはMITライセンスの下で公開されています。