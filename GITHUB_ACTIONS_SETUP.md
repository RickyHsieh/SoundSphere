# GitHub Actions �]�w���n

## �]�w GitHub Secrets

### 1. �i�J Repository Settings
1. �e���A�� GitHub repository
2. �I�� **Settings** ����
3. �������G**Secrets and variables** �� **Actions**

### 2. �s�W Repository Secrets
�I�� **New repository secret** �÷s�W�G

| Secret Name | Secret Value | ���� |
|-------------|--------------|------|
| `POSTGRES_DB` | `soundsphere` | ��Ʈw�W�� |
| `POSTGRES_USER` | `postgres` | ��Ʈw�ϥΪ� |
| `POSTGRES_PASSWORD` | `�A���w���K�X` | ��Ʈw�K�X |

## GitHub Actions �u�@�y�{

### Ĳ�o����
- **Push** �� `main` �� `develop` ����
- **Pull Request** �� `main` ����

### �u�@�y�{�]�t
1. **���ն��q** (`test` job)
   - �]�w PostgreSQL �A��
   - ����椸���թM��X����
   - �W�Ǵ��յ��G

2. **�ظm�M���p���q** (`build-and-deploy` job)
   - �Ȧb `main` ����Ĳ�o
   - �ظm JAR �ɮ�
   - �W�ǫظm����

## �w���`�N�ƶ�

- Secrets �b GitHub Actions ���O�[�K��
- ���|�b��x����� secret ��
- �u�� repository �޲z���i�H�d��/�s�� secrets
- ��ĳ�ϥαj�K�X�A���n�ϥιw�]�� `postgres`

## �����ܼƹ���

| GitHub Secret | �����ܼ� | �γ~ |
|---------------|----------|------|
| `POSTGRES_DB` | `POSTGRES_DB` | Docker Compose ��Ʈw�W�� |
| `POSTGRES_USER` | `POSTGRES_USER` | Docker Compose ��Ʈw�ϥΪ� |
| `POSTGRES_PASSWORD` | `POSTGRES_PASSWORD` | Docker Compose ��Ʈw�K�X |

## �`�����D

### Q: ���ե��ѫ���H
A: �ˬd�G
1. Secrets �O�_���T�]�w
2. PostgreSQL �A�ȬO�_���`�Ұ�
3. ���ո�Ʈw�s�u�]�w

### Q: �p��d�� Actions ���浲�G�H
A: �e�� repository �� **Actions** ���Ҭd�ݰ�����v�M�ԲӤ�x�C

### Q: �p��ק�u�@�y�{�H
A: �s�� `.github/workflows/ci.yml` �ɮר� commit �� repository�C
