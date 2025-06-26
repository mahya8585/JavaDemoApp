# Java Demo API 仕様書

## 概要
Spring Boot 3を使用したRESTful Web APIシステムです。
XMLレスポンス形式をサポートし、経費精算申請管理機能を提供します。

## ベースURL
```
http://localhost:8080/api
```

## 主要機能

### 1. 経費精算申請管理API

#### 1.1 経費精算申請情報一覧取得API
申請日が新しいものから10件を取得します。

**エンドポイント:**
```
GET /api/expense/applications
```

**レスポンス形式:** XML (application/xml)

**レスポンス例:**
```xml
<ExpenseApplicationResponse>
    <applications>
        <applications>
            <id>1</id>
            <applicationDate>2025-06-25</applicationDate>
            <applicantName>田中太郎</applicantName>
            <department>営業部</department>
            <amount>15000</amount>
            <reason>取引先との会食費</reason>
            <paymentStatus>PENDING</paymentStatus>
            <paymentStatusDisplay>申請中</paymentStatusDisplay>
        </applications>
        <!-- 他の申請情報 -->
    </applications>
    <total>10</total>
    <message>経費精算申請情報を正常に取得しました</message>
</ExpenseApplicationResponse>
```

**cURLコマンド例:**
```bash
curl -X GET http://localhost:8080/api/expense/applications \
     -H "Accept: application/xml"
```

#### 1.2 フレキシブル形式対応版（XML/JSON両対応）
**エンドポイント:**
```
GET /api/expense/applications/flexible?format=xml
GET /api/expense/applications/flexible?format=json
```

### 2. DBデータ登録API
テストデータをデータベースに登録します。

**エンドポイント:**
```
POST /api/expense/data/insert
```

**レスポンス形式:** XML (application/xml)

**レスポンス例:**
```xml
<DatabaseOperationResult>
    <success>true</success>
    <message>テストデータの登録が完了しました</message>
    <recordsInserted>12</recordsInserted>
    <executionTime>2025-06-26T10:30:00</executionTime>
    <operation>INSERT_TEST_DATA</operation>
    <details>経費精算申請データ 12件を正常に登録しました。</details>
</DatabaseOperationResult>
```

**cURLコマンド例:**
```bash
curl -X POST http://localhost:8080/api/expense/data/insert \
     -H "Accept: application/xml"
```

#### 2.1 フレキシブル形式対応版（XML/JSON両対応）
**エンドポイント:**
```
POST /api/expense/data/insert/flexible?format=xml
POST /api/expense/data/insert/flexible?format=json
```

### 3. システム管理API

#### 3.1 ヘルスチェック
```
GET /api/health
```

#### 3.2 アプリケーション情報
```
GET /api/health/info
```

## データモデル

### 経費精算申請 (ExpenseApplication)
| フィールド | 型 | 説明 |
|-----------|---|------|
| id | Long | 申請ID |
| applicationDate | LocalDate | 申請日 |
| applicantName | String | 申請者名 |
| department | String | 部署名 |
| amount | BigDecimal | 申請金額 |
| reason | String | 申請理由 |
| paymentStatus | Enum | 支払いステータス |

### 支払いステータス
- `PENDING`: 申請中
- `APPROVED`: 承認済み
- `REJECTED`: 否認  
- `PAID`: 支払い済み

## エラーレスポンス

### エラー形式（JSON）
```json
{
    "status": 404,
    "error": "Resource Not Found",
    "message": "User not found with id: 999",
    "timestamp": "2025-06-26T10:30:00",
    "validationErrors": null
}
```

### バリデーションエラー（JSON）
```json
{
    "status": 400,
    "error": "Validation Failed",
    "message": "Input validation failed",
    "timestamp": "2025-06-26T10:30:00",
    "validationErrors": {
        "name": "Name is required",
        "email": "Email should be valid"
    }
}
```

## HTTPステータスコード

| コード | 説明 |
|--------|------|
| 200 | 成功 |
| 201 | 作成成功 |
| 204 | 削除成功（コンテンツなし） |
| 400 | リクエストエラー（バリデーション失敗） |
| 404 | リソースが見つからない |
| 409 | 重複エラー |
| 500 | サーバー内部エラー |

## 開発環境での利用

### H2データベースコンソール
- URL: http://localhost:8080/api/h2-console
- JDBC URL: `jdbc:h2:mem:testdb`
- ユーザー名: `sa`
- パスワード: `password`

### Actuatorエンドポイント
- ヘルス: http://localhost:8080/api/actuator/health
- 情報: http://localhost:8080/api/actuator/info
- メトリクス: http://localhost:8080/api/actuator/metrics

## テストデータ

アプリケーション起動時に以下のテストデータが自動的に登録されます：

### 経費精算申請データ（5件）
- 各部署からの様々な申請（会食費、展示会費、書籍購入費等）

## Azure SQL Database接続

Azure SQL Databaseを使用する場合は、`application.properties`の以下の設定をアンコメントして設定してください：

```properties
# Azure SQL Database Configuration
spring.datasource.url=jdbc:sqlserver://<server>.database.windows.net:1433;database=<database>;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;
spring.datasource.username=<username>
spring.datasource.password=<password>
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver
```

## APIテスト例

### 経費精算申請情報取得（XML）
```bash
curl -X GET "http://localhost:8080/api/expense/applications" \
     -H "Accept: application/xml"
```

### テストデータ登録（XML）
```bash
curl -X POST "http://localhost:8080/api/expense/data/insert" \
     -H "Accept: application/xml"
```

### フォーマット指定での取得（JSON）
```bash
curl -X GET "http://localhost:8080/api/expense/applications/flexible?format=json" \
     -H "Accept: application/json"
```
