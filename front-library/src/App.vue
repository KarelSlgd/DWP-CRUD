<script >
export default {
  data() {
    return {
      items: [
        {
          text: "Libros",
          active: true,
        },
        {
          text: "",
          href: "#",
        },
      
      ],
    };
  },
};
</script>

<template>
  <div>
    <div class="m-5">
      <b-breadcrumb :items="items"></b-breadcrumb>
    </div>
    <div><router-view /></div>
  </div>
</template>

<style scoped>
</style>
