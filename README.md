# Java Demo API

Spring Boot 3を使用したWeb APIデモンストレーションプロジェクト

## 概要

このプロジェクトは、Spring Boot 3（最新バージョン）を使用して構築されたRESTful Web APIシステムです。経費精算申請管理機能を中心とした基本的な操作を提供します。**データベースは使用せず、メモリ上でデータを管理**します。

## 技術スタック

- **Java**: 21
- **Spring Boot**: 3.4.1
- **Spring Web**: RESTful API
- **Spring Boot Actuator**: ヘルスチェック
- **Jackson XML**: XML形式レスポンス対応
- **Maven**: ビルドツール
- **メモリベースデータ**: データベース不使用

## 主な機能

### 経費精算申請管理API（XML対応）
- 経費精算申請情報一覧取得（申請日降順、最新10件）
- XML形式でのレスポンス提供
- メモリ上での高速データアクセス

### システム管理API
- ヘルスチェック
- 経費申請情報一覧取得

## API エンドポイント

### 経費精算申請API（XML形式）
```
GET    /api/expense/applications             - 経費精算申請一覧取得（XML形式、最新10件）
```

### システムAPI
```
GET    /api/health                           - ヘルスチェック
GET    /api/health/info                      - アプリケーション情報
```

## クイックスタート

### 前提条件
- Java 21以上
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
```

## プロジェクト構造

```
src/main/java/com/example/javademoapi/
├── JavaDemoApiApplication.java    # メインアプリケーション
├── controller/                    # RESTコントローラー
│   ├── ExpenseController.java     # 経費精算申請API
│   └── HealthController.java      # ヘルスチェック
├── service/                       # ビジネスロジック
│   └── ExpenseApplicationService.java # 経費精算申請サービス
├── entity/                        # エンティティクラス（POJO）
│   └── ExpenseApplication.java    # 経費精算申請エンティティ
├── dto/                          # データ転送オブジェクト
│   ├── ExpenseApplicationResponseDto.java # 経費精算申請レスポンスDTO
│   └── DatabaseOperationResultDto.java   # 操作結果DTO
└── config/                       # 設定クラス
    └── DataLoader.java           # サンプルデータ管理
```

## 設定

アプリケーションの設定は `src/main/resources/application.properties` で管理されています。

**主要設定:**
- サーバーポート: 8080
- コンテキストパス: /api
- XML/JSON コンテンツネゴシエーション対応
- Actuatorエンドポイント有効化
- ログレベル設定

**データベース設定は削除済み** - メモリベースでの動作になります。

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

- **レイヤードアーキテクチャ**: Controller → Service → DataLoader の明確な分離
- **DTOパターン**: エンティティとAPIレスポンスの分離
- **XML対応**: Jackson XML形式でのレスポンス提供
- **メモリベースデータ**: 高速アクセスとシンプルな構成
- **サンプルデータ**: 12件の経費精算申請データを自動生成

## データ仕様

### サンプルデータ
- **件数**: 12件の経費精算申請データ
- **申請者**: 田中太郎、佐藤花子、鈴木一郎など
- **部署**: 営業部、マーケティング部、開発部など  
- **金額**: 6,800円〜25,000円
- **ステータス**: 申請中、承認済み、否認、支払い済み
- **申請日**: 現在日付から1〜12日前

### レスポンス形式（XML例）
```xml
<ExpenseApplicationResponseDto>
    <applications>
        <applications>
            <id>1</id>
            <applicationDate>2025-06-26</applicationDate>
            <applicantName>田中太郎</applicantName>
            <department>営業部</department>
            <amount>15000</amount>
            <reason>取引先との会食費</reason>
            <paymentStatus>PENDING</paymentStatus>
            <paymentStatusDisplayName>申請中</paymentStatusDisplayName>
        </applications>
    </applications>
    <totalCount>10</totalCount>
    <message>経費精算申請情報を正常に取得しました</message>
</ExpenseApplicationResponseDto>
```

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
- バリデーション機能の復活（Spring Boot Validation）
- JSON/XML フレキシブル形式の再実装

## ライセンス

このプロジェクトはapache 2.0ライセンスの下で公開されています。