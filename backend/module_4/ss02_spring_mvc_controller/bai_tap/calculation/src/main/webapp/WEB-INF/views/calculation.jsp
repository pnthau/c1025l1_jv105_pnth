<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nexus Calculator</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Outfit:wght@300;400;600&display=swap');

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Outfit', sans-serif;
        }

        body {
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background: linear-gradient(135deg, #0f2027, #203a43, #2c5364);
            color: #fff;
            overflow: hidden;
        }

        /* Animated background elements */
        .circle {
            position: absolute;
            border-radius: 50%;
            background: linear-gradient(135deg, rgba(0,255,204,0.4), rgba(0,204,255,0.4));
            filter: blur(40px);
            animation: float 8s infinite ease-in-out alternate;
            z-index: 0;
        }
        .circle-1 { width: 300px; height: 300px; top: 10%; left: 20%; animation-delay: 0s; }
        .circle-2 { width: 400px; height: 400px; bottom: 10%; right: 15%; background: linear-gradient(135deg, rgba(255,0,150,0.3), rgba(204,0,255,0.3)); animation-delay: -4s; }

        @keyframes float {
            0% { transform: translateY(0) scale(1); }
            100% { transform: translateY(-30px) scale(1.1); }
        }

        .calculator-container {
            position: relative;
            z-index: 1;
            background: rgba(255, 255, 255, 0.05);
            backdrop-filter: blur(16px);
            -webkit-backdrop-filter: blur(16px);
            border: 1px solid rgba(255, 255, 255, 0.1);
            border-radius: 24px;
            padding: 40px;
            width: 100%;
            max-width: 420px;
            box-shadow: 0 25px 45px rgba(0, 0, 0, 0.2);
            transition: transform 0.3s ease;
        }

        .calculator-container:hover {
            transform: translateY(-5px);
        }

        .title {
            text-align: center;
            font-size: 28px;
            font-weight: 600;
            margin-bottom: 30px;
            letter-spacing: 1px;
            background: linear-gradient(to right, #00ffcc, #00ccff);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
        }

        .result-display {
            background: rgba(0, 0, 0, 0.2);
            border-radius: 12px;
            padding: 20px;
            margin-bottom: 25px;
            text-align: right;
            font-size: 32px;
            font-weight: 300;
            min-height: 80px;
            display: flex;
            flex-direction: column;
            justify-content: center;
            border: 1px solid rgba(255, 255, 255, 0.05);
            box-shadow: inset 0 4px 6px rgba(0,0,0,0.1);
            overflow-wrap: break-word;
        }

        .result-display .expression-text {
            font-size: 14px;
            color: rgba(255, 255, 255, 0.6);
            margin-bottom: 5px;
        }

        .input-group {
            margin-bottom: 20px;
            position: relative;
        }

        .input-group input {
            width: 100%;
            padding: 15px 20px;
            background: rgba(255, 255, 255, 0.08);
            border: 1px solid rgba(255, 255, 255, 0.1);
            border-radius: 12px;
            color: #fff;
            font-size: 18px;
            outline: none;
            transition: all 0.3s ease;
        }

        .input-group input:focus {
            background: rgba(255, 255, 255, 0.12);
            border-color: rgba(0, 255, 204, 0.5);
            box-shadow: 0 0 15px rgba(0, 255, 204, 0.2);
        }

        .input-group input::placeholder {
            color: rgba(255, 255, 255, 0.4);
        }

        .btn-group {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 15px;
            margin-top: 30px;
        }

        .btn-op {
            background: rgba(255, 255, 255, 0.1);
            border: 1px solid rgba(255, 255, 255, 0.1);
            color: #fff;
            font-size: 24px;
            padding: 15px 0;
            border-radius: 12px;
            cursor: pointer;
            transition: all 0.2s ease;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .btn-op:hover {
            background: rgba(255, 255, 255, 0.2);
            transform: scale(1.05);
        }

        .btn-op:active {
            transform: scale(0.95);
        }

        /* Specialized colors for buttons */
        .btn-op[value="add"] { background: rgba(0, 255, 136, 0.15); border-color: rgba(0, 255, 136, 0.3); }
        .btn-op[value="subtract"] { background: rgba(255, 107, 107, 0.15); border-color: rgba(255, 107, 107, 0.3); }
        .btn-op[value="multiply"] { background: rgba(77, 171, 247, 0.15); border-color: rgba(77, 171, 247, 0.3); }
        .btn-op[value="divide"] { background: rgba(206, 145, 255, 0.15); border-color: rgba(206, 145, 255, 0.3); }

        .btn-op[value="add"]:hover { background: rgba(0, 255, 136, 0.3); box-shadow: 0 0 15px rgba(0, 255, 136, 0.4); }
        .btn-op[value="subtract"]:hover { background: rgba(255, 107, 107, 0.3); box-shadow: 0 0 15px rgba(255, 107, 107, 0.4); }
        .btn-op[value="multiply"]:hover { background: rgba(77, 171, 247, 0.3); box-shadow: 0 0 15px rgba(77, 171, 247, 0.4); }
        .btn-op[value="divide"]:hover { background: rgba(206, 145, 255, 0.3); box-shadow: 0 0 15px rgba(206, 145, 255, 0.4); }

    </style>
</head>
<body>
    <!-- Background aesthetic elements -->
    <div class="circle circle-1"></div>
    <div class="circle circle-2"></div>

    <div class="calculator-container">
        <h2 class="title">Nexus Calc</h2>
        
        <div class="result-display">
            <!-- Show expression if it exists -->



            <!-- Show result if it exists, otherwise show 0 -->
            <div>
                <c:choose>
                    <c:when test="${not empty result}">${result}</c:when>
                    <c:when test="${not empty errorMsg}">
                        <span style="color: #ff6b6b; font-size: 24px;">NaN</span>
                    </c:when>
                    <c:otherwise>
                        0
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <form action="${pageContext.request.contextPath}/calculation" method="post">
            <div class="input-group">
                <input type="number" name="a" placeholder="First Number (a)" value="${expression.a}" required autocomplete="off">
            </div>
            <div class="input-group">
                <input type="number" name="b" placeholder="Second Number (b)" value="${expression.b}" required autocomplete="off">
            </div>

            <div class="btn-group">
                <button type="submit" name="operation" value="+" class="btn-op">+</button>
                <button type="submit" name="operation" value="-" class="btn-op">−</button>
                <button type="submit" name="operation" value="*" class="btn-op">×</button>
                <button type="submit" name="operation" value="/" class="btn-op">÷</button>
            </div>
        </form>
    </div>
</body>
</html>
