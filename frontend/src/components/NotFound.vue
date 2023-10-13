<template>
  <div class="history">
    <div class="select-block">
      <Card class="select-block-card">

      </Card>
    </div>
  </div>
</template>

<script>
export default {
  name: "NotFound"
}
</script>

<style scoped>

.select-block-card, .history-block-card {
  height: 98vh;
  background: #FFFFFF;
  border-radius: 15px;
  margin-left: 0.5rem;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.select-block, .history-block {
  width: 100%;
}

.history {
  display: flex;
  width: 94%;
}
</style>