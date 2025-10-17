# GitHub Actions 設定指南

## 設定 GitHub Secrets

### 1. 進入 Repository Settings
1. 前往你的 GitHub repository
2. 點擊 **Settings** 標籤
3. 左側選單：**Secrets and variables** → **Actions**

### 2. 新增 Repository Secrets
點擊 **New repository secret** 並新增：

| Secret Name | Secret Value | 說明 |
|-------------|--------------|------|
| `POSTGRES_DB` | `soundsphere` | 資料庫名稱 |
| `POSTGRES_USER` | `postgres` | 資料庫使用者 |
| `POSTGRES_PASSWORD` | `你的安全密碼` | 資料庫密碼 |

## GitHub Actions 工作流程

### 觸發條件
- **Push** 到 `main` 或 `develop` 分支
- **Pull Request** 到 `main` 分支

### 工作流程包含
1. **測試階段** (`test` job)
   - 設定 PostgreSQL 服務
   - 執行單元測試和整合測試
   - 上傳測試結果

2. **建置和部署階段** (`build-and-deploy` job)
   - 僅在 `main` 分支觸發
   - 建置 JAR 檔案
   - 上傳建置產物

## 安全注意事項

- Secrets 在 GitHub Actions 中是加密的
- 不會在日誌中顯示 secret 值
- 只有 repository 管理員可以查看/編輯 secrets
- 建議使用強密碼，不要使用預設的 `postgres`

## 環境變數對應

| GitHub Secret | 環境變數 | 用途 |
|---------------|----------|------|
| `POSTGRES_DB` | `POSTGRES_DB` | Docker Compose 資料庫名稱 |
| `POSTGRES_USER` | `POSTGRES_USER` | Docker Compose 資料庫使用者 |
| `POSTGRES_PASSWORD` | `POSTGRES_PASSWORD` | Docker Compose 資料庫密碼 |

## 常見問題

### Q: 測試失敗怎麼辦？
A: 檢查：
1. Secrets 是否正確設定
2. PostgreSQL 服務是否正常啟動
3. 測試資料庫連線設定

### Q: 如何查看 Actions 執行結果？
A: 前往 repository 的 **Actions** 標籤查看執行歷史和詳細日誌。

### Q: 如何修改工作流程？
A: 編輯 `.github/workflows/ci.yml` 檔案並 commit 到 repository。
